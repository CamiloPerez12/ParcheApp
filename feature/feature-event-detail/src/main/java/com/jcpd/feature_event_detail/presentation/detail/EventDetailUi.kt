package com.jcpd.feature_event_detail.presentation.detail

import com.jcpd.core_ui.components.EventCardState
import com.jcpd.core_ui.components.EventSportType
import com.jcpd.core_ui.components.ParcheStatusBadgeType

data class EventDetailUi(
    val id: String,
    val title: String,
    val description: String,
    val sportType: EventSportType,
    val statusText: String,
    val statusType: ParcheStatusBadgeType,
    val state: EventCardState,

    val locationName: String,
    val address: String,
    val distanceKm: Double,

    val dateLabel: String,
    val timeLabel: String,
    val levelLabel: String,
    val priceLabel: String?,

    val joinedPlayers: Int,
    val totalPlayers: Int,
    val remainingSpots: Int,
    val rating: Double,

    val organizer: OrganizerUi,
    val participants: List<ParticipantUi>
)

data class OrganizerUi(
    val id: String,
    val name: String,
    val username: String,
    val city: String,
    val rating: Double,
    val isVerified: Boolean,
    val attendancePercentage: Int
)

data class ParticipantUi(
    val id: String,
    val name: String,
    val initials: String,
    val rating: Double,
    val isVerified: Boolean,
    val isOrganizer: Boolean = false
)