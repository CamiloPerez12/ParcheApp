package com.jcpd.feature_profile.presentation.profile

data class ProfileUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val profile: ProfileUi? = null
)

data class ProfileUi(
    val fullName: String,
    val username: String,
    val city: String,
    val bio: String,
    val rating: String,
    val eventsJoined: String,
    val reliability: String,
    val preferredSports: List<String>
)