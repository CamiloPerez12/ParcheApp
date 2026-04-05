package com.jcpd.feature_profile.domain.repository

import com.jcpd.feature_profile.domain.model.UserProfile

interface ProfileRepository {
    suspend fun getProfile(): UserProfile
}