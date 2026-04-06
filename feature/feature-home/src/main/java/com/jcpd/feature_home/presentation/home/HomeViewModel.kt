package com.jcpd.feature_home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcpd.core_common.session.JoinedEventsRepository
import com.jcpd.core_ui.components.EventCardState
import com.jcpd.core_ui.components.EventSportType
import com.jcpd.feature_home.R
import com.jcpd.feature_home.domain.model.HomeEvent
import com.jcpd.feature_home.domain.usecase.GetHomeQuickStatsUseCase
import com.jcpd.feature_home.domain.usecase.GetNearbyEventsUseCase
import com.jcpd.feature_home.domain.usecase.GetSelectedLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNearbyEventsUseCase: GetNearbyEventsUseCase,
    private val getHomeQuickStatsUseCase: GetHomeQuickStatsUseCase,
    private val getSelectedLocationUseCase: GetSelectedLocationUseCase,
    private val joinedEventsRepository: JoinedEventsRepository
) : ViewModel() {

    private val _baseUiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _baseUiState.asStateFlow()

    init {
        loadHome()
        observeJoinedEvents()
    }

    private fun observeJoinedEvents() {
        viewModelScope.launch {
            joinedEventsRepository.joinedEventIds.collect {
                val current = _baseUiState.value
                if (current.allEvents.isNotEmpty()) {
                    val updatedAllEvents = current.allEvents.map { it.withJoinedState() }
                    val filteredEvents = filterEvents(
                        updatedAllEvents,
                        current.selectedCategoryId ?: "all"
                    )

                    _baseUiState.value = current.copy(
                        allEvents = updatedAllEvents,
                        events = filteredEvents,
                        isEmpty = filteredEvents.isEmpty()
                    )
                }
            }
        }
    }

    fun loadHome() {
        viewModelScope.launch {
            _baseUiState.value = _baseUiState.value.copy(
                isLoading = true,
                errorMessageRes = null
            )

            runCatching {
                val location = getSelectedLocationUseCase()
                val stats = getHomeQuickStatsUseCase()
                val events = getNearbyEventsUseCase()
                    .map { it.toUi() }
                    .map { it.withJoinedState() }

                val defaultSelectedCategory = "all"
                val filteredEvents = filterEvents(events, defaultSelectedCategory)

                HomeUiState(
                    isLoading = false,
                    isRefreshing = false,
                    isEmpty = filteredEvents.isEmpty(),
                    errorMessageRes = null,
                    greetingRes = R.string.home_greeting_afternoon,
                    titleRes = R.string.home_title,
                    locationLabel = location,
                    searchPlaceholderRes = R.string.home_search_placeholder,
                    unreadNotificationsCount = 3,
                    quickStats = stats.map {
                        HomeQuickStatUi(
                            id = it.id,
                            value = it.value,
                            label = it.label
                        )
                    },
                    categories = defaultCategories(selectedCategoryId = defaultSelectedCategory),
                    selectedCategoryId = defaultSelectedCategory,
                    allEvents = events,
                    events = filteredEvents,
                    showMapShortcut = true,
                    mapShortcutLabelRes = R.string.home_map_shortcut
                )
            }.onSuccess { newState ->
                _baseUiState.value = newState
            }.onFailure {
                _baseUiState.value = _baseUiState.value.copy(
                    isLoading = false,
                    isRefreshing = false,
                    isEmpty = false,
                    errorMessageRes = R.string.home_error_generic
                )
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _baseUiState.value = _baseUiState.value.copy(
                isRefreshing = true,
                errorMessageRes = null
            )

            runCatching {
                getNearbyEventsUseCase()
                    .map { it.toUi() }
                    .map { it.withJoinedState() }
            }.onSuccess { events ->
                val selectedCategory = _baseUiState.value.selectedCategoryId ?: "all"
                val filteredEvents = filterEvents(events, selectedCategory)

                _baseUiState.value = _baseUiState.value.copy(
                    isRefreshing = false,
                    allEvents = events,
                    events = filteredEvents,
                    isEmpty = filteredEvents.isEmpty()
                )
            }.onFailure {
                _baseUiState.value = _baseUiState.value.copy(
                    isRefreshing = false,
                    errorMessageRes = R.string.home_error_refresh
                )
            }
        }
    }

    fun onCategorySelected(categoryId: String) {
        val updatedCategories = defaultCategories(selectedCategoryId = categoryId)
        val filteredEvents = filterEvents(_baseUiState.value.allEvents, categoryId)

        _baseUiState.value = _baseUiState.value.copy(
            selectedCategoryId = categoryId,
            categories = updatedCategories,
            events = filteredEvents,
            isEmpty = filteredEvents.isEmpty()
        )
    }

    private fun defaultCategories(selectedCategoryId: String): List<HomeCategoryUi> {
        return listOf(
            HomeCategoryUi("all", R.string.category_all, selectedCategoryId == "all"),
            HomeCategoryUi("futbol", R.string.category_futbol, selectedCategoryId == "futbol"),
            HomeCategoryUi("tenis", R.string.category_tenis, selectedCategoryId == "tenis"),
            HomeCategoryUi("basket", R.string.category_basket, selectedCategoryId == "basket"),
            HomeCategoryUi("social", R.string.category_social, selectedCategoryId == "social")
        )
    }

    private fun filterEvents(
        events: List<HomeEventUi>,
        categoryId: String
    ): List<HomeEventUi> {
        return when (categoryId) {
            "all" -> events
            "futbol" -> events.filter { it.sportType == EventSportType.Futbol }
            "tenis" -> events.filter { it.sportType == EventSportType.Tenis }
            "basket" -> events.filter { it.sportType == EventSportType.Basket }
            "social" -> events.filter { it.sportType == EventSportType.Social }
            else -> events
        }
    }

    private fun HomeEventUi.withJoinedState(): HomeEventUi {
        val resolvedState = when {
            state == EventCardState.Full -> EventCardState.Full
            joinedEventsRepository.isJoined(id) -> EventCardState.Joined
            else -> EventCardState.Default
        }
        return copy(state = resolvedState)
    }
}

private fun HomeEvent.toUi(): HomeEventUi {
    return HomeEventUi(
        id = id,
        title = title,
        locationName = locationName,
        distanceKm = distanceKm,
        dateLabel = dateLabel,
        joinedPlayers = joinedPlayers,
        totalPlayers = totalPlayers,
        levelLabel = levelLabel,
        priceLabel = priceLabel,
        statusLabel = statusLabel,
        statusType = statusType,
        rating = rating,
        remainingSpots = remainingSpots,
        state = state,
        sportType = sportType,
        participantInitials = participantInitials
    )
}