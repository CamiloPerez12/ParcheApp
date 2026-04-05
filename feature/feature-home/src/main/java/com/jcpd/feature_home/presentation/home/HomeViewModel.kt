package com.jcpd.feature_home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNearbyEventsUseCase: GetNearbyEventsUseCase,
    private val getHomeQuickStatsUseCase: GetHomeQuickStatsUseCase,
    private val getSelectedLocationUseCase: GetSelectedLocationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHome()
    }

    fun loadHome() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessageRes = null
            )

            runCatching {
                val location = getSelectedLocationUseCase()
                val stats = getHomeQuickStatsUseCase()
                val events = getNearbyEventsUseCase().map { it.toUi() }

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
                _uiState.value = newState
            }.onFailure {
                _uiState.value = _uiState.value.copy(
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
            _uiState.value = _uiState.value.copy(
                isRefreshing = true,
                errorMessageRes = null
            )

            runCatching {
                getNearbyEventsUseCase().map { it.toUi() }
            }.onSuccess { events ->
                val selectedCategory = _uiState.value.selectedCategoryId ?: "all"
                val filteredEvents = filterEvents(events, selectedCategory)

                _uiState.value = _uiState.value.copy(
                    isRefreshing = false,
                    allEvents = events,
                    events = filteredEvents,
                    isEmpty = filteredEvents.isEmpty()
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    isRefreshing = false,
                    errorMessageRes = R.string.home_error_refresh
                )
            }
        }
    }

    fun onCategorySelected(categoryId: String) {
        val updatedCategories = defaultCategories(selectedCategoryId = categoryId)
        val filteredEvents = filterEvents(_uiState.value.allEvents, categoryId)

        _uiState.value = _uiState.value.copy(
            selectedCategoryId = categoryId,
            categories = updatedCategories,
            events = filteredEvents,
            isEmpty = filteredEvents.isEmpty()
        )
    }

    private fun defaultCategories(selectedCategoryId: String): List<HomeCategoryUi> {
        return listOf(
            HomeCategoryUi(
                id = "all",
                labelRes = R.string.category_all,
                isSelected = selectedCategoryId == "all"
            ),
            HomeCategoryUi(
                id = "futbol",
                labelRes = R.string.category_futbol,
                isSelected = selectedCategoryId == "futbol"
            ),
            HomeCategoryUi(
                id = "tenis",
                labelRes = R.string.category_tenis,
                isSelected = selectedCategoryId == "tenis"
            ),
            HomeCategoryUi(
                id = "basket",
                labelRes = R.string.category_basket,
                isSelected = selectedCategoryId == "basket"
            ),
            HomeCategoryUi(
                id = "social",
                labelRes = R.string.category_social,
                isSelected = selectedCategoryId == "social"
            )
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