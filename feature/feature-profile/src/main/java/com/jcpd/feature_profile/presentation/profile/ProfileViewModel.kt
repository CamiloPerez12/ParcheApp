package com.jcpd.feature_profile.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcpd.feature_profile.domain.model.UserProfile
import com.jcpd.feature_profile.domain.usecase.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.DecimalFormat

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState(isLoading = true))
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            runCatching {
                getProfileUseCase()
            }.onSuccess { profile ->
                _uiState.value = ProfileUiState(
                    isLoading = false,
                    profile = profile.toUi()
                )
            }.onFailure {
                _uiState.value = ProfileUiState(
                    isLoading = false,
                    errorMessage = "No se pudo cargar el perfil"
                )
            }
        }
    }
}

private fun UserProfile.toUi(): ProfileUi {
    return ProfileUi(
        fullName = fullName,
        username = username,
        city = city,
        bio = bio,
        rating = DecimalFormat("#.#").format(rating),
        eventsJoined = eventsJoined.toString(),
        reliability = "$reliability%",
        preferredSports = preferredSports
    )
}