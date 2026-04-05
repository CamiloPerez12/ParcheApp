package com.jcpd.feature_event_detail.presentation.detail

data class EventDetailUiState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorMessageRes: Int? = null,

    val titleRes: Int? = null,
    val joinButtonTextRes: Int? = null,
    val openChatButtonTextRes: Int? = null,
    val organizerSectionTitleRes: Int? = null,
    val participantsSectionTitleRes: Int? = null,
    val locationSectionTitleRes: Int? = null,
    val detailsSectionTitleRes: Int? = null,

    val eventDetail: EventDetailUi? = null
)