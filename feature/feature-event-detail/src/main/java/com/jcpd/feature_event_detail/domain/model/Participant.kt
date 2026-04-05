package com.jcpd.feature_event_detail.domain.model

data class Participant(
    val id: String,
    val name: String,
    val initials: String,
    val rating: Double,
    val isVerified: Boolean,
    val isOrganizer: Boolean = false
)