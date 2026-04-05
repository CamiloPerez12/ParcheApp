package com.jcpd.feature_profile.domain.model

data class UserProfile(
    val id: String,
    val fullName: String,
    val username: String,
    val city: String,
    val bio: String,
    val rating: Double,
    val eventsJoined: Int,
    val reliability: Int,
    val preferredSports: List<String>
)