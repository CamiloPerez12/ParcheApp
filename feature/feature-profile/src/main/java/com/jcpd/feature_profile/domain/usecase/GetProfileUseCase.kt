package com.jcpd.feature_profile.domain.usecase

import com.jcpd.feature_profile.domain.model.UserProfile
import com.jcpd.feature_profile.domain.repository.ProfileRepository

class GetProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): UserProfile {
        return repository.getProfile()
    }
}