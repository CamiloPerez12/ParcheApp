package com.jcpd.feature_home.data.repository

import com.jcpd.core_ui.components.EventCardState
import com.jcpd.core_ui.components.EventSportType
import com.jcpd.core_ui.components.ParcheStatusBadgeType
import com.jcpd.feature_home.domain.model.HomeEvent
import com.jcpd.feature_home.domain.model.HomeQuickStat
import com.jcpd.feature_home.domain.repository.HomeRepository
import kotlinx.coroutines.delay

class FakeHomeRepository : HomeRepository {

    override suspend fun getNearbyEvents(): List<HomeEvent> {
        delay(400)

        return listOf(
            HomeEvent(
                id = "event_1",
                title = "Fútbol 5 en Galerías",
                locationName = "Cancha El Parque",
                distanceKm = 1.2,
                dateLabel = "Hoy • 7:00 PM",
                joinedPlayers = 8,
                totalPlayers = 10,
                levelLabel = "Intermedio",
                priceLabel = "$18.000",
                statusLabel = "EN 2H",
                statusType = ParcheStatusBadgeType.Soon,
                rating = 4.8,
                remainingSpots = 2,
                state = EventCardState.Default,
                sportType = EventSportType.Futbol,
                participantInitials = listOf("CM", "JL", "PA", "DV")
            ),
            HomeEvent(
                id = "event_2",
                title = "Tenis en Chapinero",
                locationName = "Club Norte",
                distanceKm = 2.4,
                dateLabel = "Mañana • 6:30 PM",
                joinedPlayers = 2,
                totalPlayers = 4,
                levelLabel = "Principiante",
                priceLabel = "$25.000",
                statusLabel = "MAÑANA",
                statusType = ParcheStatusBadgeType.Later,
                rating = 4.6,
                remainingSpots = 2,
                state = EventCardState.Default,
                sportType = EventSportType.Tenis,
                participantInitials = listOf("LR", "MA")
            ),
            HomeEvent(
                id = "event_3",
                title = "Basket en Teusaquillo",
                locationName = "Parque Central",
                distanceKm = 0.8,
                dateLabel = "Hoy • 8:00 PM",
                joinedPlayers = 10,
                totalPlayers = 10,
                levelLabel = "Avanzado",
                priceLabel = null,
                statusLabel = "LLENO",
                statusType = ParcheStatusBadgeType.Full,
                rating = 4.9,
                remainingSpots = 0,
                state = EventCardState.Full,
                sportType = EventSportType.Basket,
                participantInitials = listOf("JP", "AN", "CR", "SV")
            )
        )
    }

    override suspend fun getQuickStats(): List<HomeQuickStat> {
        delay(150)

        return listOf(
            HomeQuickStat(
                id = "stat_1",
                value = "12",
                label = "partidos cerca"
            ),
            HomeQuickStat(
                id = "stat_2",
                value = "4",
                label = "empiezan pronto"
            ),
            HomeQuickStat(
                id = "stat_3",
                value = "3",
                label = "amigos jugando"
            )
        )
    }

    override suspend fun getSelectedLocation(): String {
        delay(100)
        return "Bogotá"
    }
}