package com.jcpd.feature_home.presentation.home

data class HomeUiState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isEmpty: Boolean = false,
    val errorMessageRes: Int? = null,

    val greetingRes: Int? = null,
    val titleRes: Int? = null,
    val locationLabel: String = "",
    val searchPlaceholderRes: Int? = null,

    val unreadNotificationsCount: Int = 0,

    val quickStats: List<HomeQuickStatUi> = emptyList(),
    val categories: List<HomeCategoryUi> = emptyList(),
    val selectedCategoryId: String? = null,

    val events: List<HomeEventUi> = emptyList(),

    val showMapShortcut: Boolean = true,
    val mapShortcutLabelRes: Int? = null
)