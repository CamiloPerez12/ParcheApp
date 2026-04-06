package com.jcpd.feature_event_detail.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcpd.core_common.session.JoinedEventsRepository
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
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val eventId: String = savedStateHandle["eventId"] ?: ""

    private val _uiState = MutableStateFlow(EventDetailUiState(isLoading = true))
    val uiState: StateFlow<EventDetailUiState> = _uiState.asStateFlow()

    init {
        loadEventDetail()
        observeJoinedState()
    }

    private fun observeJoinedState() {
        viewModelScope.launch {
            joinedEventsRepository.joinedEventIds.collect {
                val currentEvent = _uiState.value.eventDetail ?: return@collect
                val updatedEvent = if (joinedEventsRepository.isJoined(currentEvent.id)
                    && currentEvent.state != EventCardState.Full
                ) {
                    currentEvent.copy(state = EventCardState.Joined)
                } else {
                    currentEvent
                }

                _uiState.value = _uiState.value.copy(
                    joinButtonTextRes = resolveJoinButtonText(updatedEvent.state),
                    eventDetail = updatedEvent
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
                val detail = getEventDetailUseCase(eventId).toUi().withJoinedState()

                EventDetailUiState(
                    isLoading = false,
                    isRefreshing = false,
                    errorMessageRes = null,
                    titleRes = R.string.event_detail_title,
                    joinButtonTextRes = resolveJoinButtonText(detail.state),
                    openChatButtonTextRes = R.string.event_detail_open_chat,
                    organizerSectionTitleRes = R.string.event_detail_organizer_section,
                    participantsSectionTitleRes = R.string.event_detail_participants_section,
                    locationSectionTitleRes = R.string.event_detail_location_section,
                    detailsSectionTitleRes = R.string.event_detail_details_section,
                    eventDetail = detail
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
                getEventDetailUseCase(eventId).toUi().withJoinedState()
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

    private fun resolveJoinButtonText(state: EventCardState): Int {
        return when (state) {
            EventCardState.Default -> R.string.event_detail_join
            EventCardState.Joined -> R.string.event_detail_open_chat
            EventCardState.Full -> R.string.event_detail_full
        }
    }

    private fun EventDetailUi.withJoinedState(): EventDetailUi {
        return if (joinedEventsRepository.isJoined(id) && state != EventCardState.Full) {
            copy(state = EventCardState.Joined)
        } else {
            this
        }
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