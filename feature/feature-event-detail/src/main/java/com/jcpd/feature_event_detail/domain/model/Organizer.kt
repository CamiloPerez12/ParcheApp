package com.jcpd.feature_event_detail.domain.model

data class Organizer(
    val id: String,
    val name: String,
    val username: String,
    val city: String,
    val rating: Double,
    val isVerified: Boolean,
    val attendancePercentage: Int
)