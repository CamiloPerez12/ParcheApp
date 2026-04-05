package com.jcpd.feature_event_detail.domain.model

import com.jcpd.core_ui.components.EventCardState
import com.jcpd.core_ui.components.EventSportType
import com.jcpd.core_ui.components.ParcheStatusBadgeType

data class EventDetail(
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

    val organizer: Organizer,
    val participants: List<Participant>
)