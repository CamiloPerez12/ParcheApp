package com.jcpd.feature_home.presentation.home

import com.jcpd.core_ui.components.EventCardState
import com.jcpd.core_ui.components.EventSportType
import com.jcpd.core_ui.components.ParcheStatusBadgeType

data class HomeEventUi(
    val id: String,
    val title: String,
    val locationName: String,
    val distanceKm: Double,
    val dateLabel: String,
    val joinedPlayers: Int,
    val totalPlayers: Int,
    val levelLabel: String,
    val priceLabel: String? = null,
    val statusLabel: String,
    val statusType: ParcheStatusBadgeType,
    val rating: Double,
    val remainingSpots: Int,
    val state: EventCardState,
    val sportType: EventSportType,
    val participantInitials: List<String> = emptyList()
)