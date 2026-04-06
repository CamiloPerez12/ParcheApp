package com.jcpd.feature_event_detail.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcpd.core_common.session.JoinedEventsRepository
import com.jcpd.core_common.session.SessionUser
import com.jcpd.core_common.session.UserSessionRepository
import com.jcpd.core_ui.components.EventCardState
import com.jcpd.feature_event_detail.R
import com.jcpd.feature_event_detail.domain.model.EventDetail
import com.jcpd.feature_event_detail.domain.usecase.GetEventDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    private val getEventDetailUseCase: GetEventDetailUseCase,
    private val joinedEventsRepository: JoinedEventsRepository,
    private val userSessionRepository: UserSessionRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val eventId: String = savedStateHandle["eventId"] ?: ""

    private val _uiState = MutableStateFlow(EventDetailUiState(isLoading = true))
    val uiState: StateFlow<EventDetailUiState> = _uiState.asStateFlow()

    private var baseEventDetail: EventDetail? = null

    init {
        loadEventDetail()
        observeJoinedState()
    }

    private fun observeJoinedState() {
        viewModelScope.launch {
            joinedEventsRepository.joinedEventIds.collect {
                val baseEvent = baseEventDetail ?: return@collect
                val updatedDetail = baseEvent.toUi().resolveDynamicState()

                _uiState.value = _uiState.value.copy(
                    joinButtonTextRes = resolveJoinButtonText(updatedDetail.state),
                    eventDetail = updatedDetail
                )
            }
        }
    }

    fun loadEventDetail() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessageRes = null
            )

            runCatching {
                val detail = getEventDetailUseCase(eventId)
                baseEventDetail = detail

                val uiDetail = detail.toUi().resolveDynamicState()

                EventDetailUiState(
                    isLoading = false,
                    isRefreshing = false,
                    errorMessageRes = null,
                    titleRes = R.string.event_detail_title,
                    joinButtonTextRes = resolveJoinButtonText(uiDetail.state),
                    openChatButtonTextRes = R.string.event_detail_open_chat,
                    organizerSectionTitleRes = R.string.event_detail_organizer_section,
                    participantsSectionTitleRes = R.string.event_detail_participants_section,
                    locationSectionTitleRes = R.string.event_detail_location_section,
                    detailsSectionTitleRes = R.string.event_detail_details_section,
                    eventDetail = uiDetail
                )
            }.onSuccess { state ->
                _uiState.value = state
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isRefreshing = false,
                    errorMessageRes = R.string.event_detail_error_generic
                )
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isRefreshing = true,
                errorMessageRes = null
            )

            runCatching {
                val detail = getEventDetailUseCase(eventId)
                baseEventDetail = detail
                detail.toUi().resolveDynamicState()
            }.onSuccess { detail ->
                _uiState.value = _uiState.value.copy(
                    isRefreshing = false,
                    joinButtonTextRes = resolveJoinButtonText(detail.state),
                    eventDetail = detail
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    isRefreshing = false,
                    errorMessageRes = R.string.event_detail_error_refresh
                )
            }
        }
    }

    fun joinCurrentEvent() {
        viewModelScope.launch {
            joinedEventsRepository.joinEvent(eventId)
        }
    }

    fun leaveCurrentEvent() {
        viewModelScope.launch {
            joinedEventsRepository.leaveEvent(eventId)
        }
    }

    private fun resolveJoinButtonText(state: EventCardState): Int {
        return when (state) {
            EventCardState.Default -> R.string.event_detail_join
            EventCardState.Joined -> R.string.event_detail_open_chat
            EventCardState.Full -> R.string.event_detail_full
        }
    }

    private fun EventDetailUi.resolveDynamicState(): EventDetailUi {
        val currentUser = userSessionRepository.currentUser.value
        val isJoined = joinedEventsRepository.isJoined(id)

        val updatedParticipants = buildParticipants(
            baseParticipants = participants,
            organizerId = organizer.id,
            isJoined = isJoined,
            currentUser = currentUser
        )

        val updatedJoinedPlayers = updatedParticipants.size
        val updatedRemainingSpots = (totalPlayers - updatedJoinedPlayers).coerceAtLeast(0)

        val finalState = when {
            updatedRemainingSpots == 0 && !isJoined -> EventCardState.Full
            isJoined -> EventCardState.Joined
            else -> EventCardState.Default
        }

        return copy(
            state = finalState,
            participants = updatedParticipants,
            joinedPlayers = updatedJoinedPlayers,
            remainingSpots = updatedRemainingSpots
        )
    }

    private fun buildParticipants(
        baseParticipants: List<ParticipantUi>,
        organizerId: String,
        isJoined: Boolean,
        currentUser: SessionUser
    ): List<ParticipantUi> {
        val alreadyIncluded = baseParticipants.any { it.id == currentUser.id }

        val currentUserParticipant = ParticipantUi(
            id = currentUser.id,
            name = currentUser.name,
            initials = currentUser.name.toInitials(),
            rating = currentUser.rating,
            isVerified = currentUser.isVerified,
            isOrganizer = currentUser.id == organizerId
        )

        return when {
            isJoined && !alreadyIncluded -> listOf(currentUserParticipant) + baseParticipants
            !isJoined -> baseParticipants.filterNot { it.id == currentUser.id }
            else -> baseParticipants
        }
    }

    private fun String.toInitials(): String {
        return trim()
            .split("\\s+".toRegex())
            .filter { it.isNotBlank() }
            .take(2)
            .joinToString("") { it.first().uppercase() }
    }
}

private fun EventDetail.toUi(): EventDetailUi {
    return EventDetailUi(
        id = id,
        title = title,
        description = description,
        sportType = sportType,
        statusText = statusText,
        statusType = statusType,
        state = state,
        locationName = locationName,
        address = address,
        distanceKm = distanceKm,
        dateLabel = dateLabel,
        timeLabel = timeLabel,
        levelLabel = levelLabel,
        priceLabel = priceLabel,
        joinedPlayers = joinedPlayers,
        totalPlayers = totalPlayers,
        remainingSpots = remainingSpots,
        rating = rating,
        organizer = OrganizerUi(
            id = organizer.id,
            name = organizer.name,
            username = organizer.username,
            city = organizer.city,
            rating = organizer.rating,
            isVerified = organizer.isVerified,
            attendancePercentage = organizer.attendancePercentage
        ),
        participants = participants.map {
            ParticipantUi(
                id = it.id,
                name = it.name,
                initials = it.initials,
                rating = it.rating,
                isVerified = it.isVerified,
                isOrganizer = it.isOrganizer
            )
        }
    )
}