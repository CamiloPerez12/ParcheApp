package com.jcpd.feature_event_detail.data.repository

import com.jcpd.core_ui.components.EventCardState
import com.jcpd.core_ui.components.EventSportType
import com.jcpd.core_ui.components.ParcheStatusBadgeType
import com.jcpd.feature_event_detail.domain.model.EventDetail
import com.jcpd.feature_event_detail.domain.model.Organizer
import com.jcpd.feature_event_detail.domain.model.Participant
import com.jcpd.feature_event_detail.domain.repository.EventDetailRepository
import kotlinx.coroutines.delay

class FakeEventDetailRepository : EventDetailRepository {

    override suspend fun getEventDetail(eventId: String): EventDetail {
        delay(300)

        return EventDetail(
            id = eventId,
            title = "Fútbol 5 en Galerías",
            description = "Partido casual para completar equipo. Buen ambiente, nivel intermedio y puntualidad importante.",
            sportType = EventSportType.Futbol,
            statusText = "EN 2H",
            statusType = ParcheStatusBadgeType.Soon,
            state = EventCardState.Default,

            locationName = "Cancha El Parque",
            address = "Cra. 24 #53-21, Bogotá",
            distanceKm = 1.2,

            dateLabel = "Hoy",
            timeLabel = "7:00 PM",
            levelLabel = "Intermedio",
            priceLabel = "$18.000",

            joinedPlayers = 8,
            totalPlayers = 10,
            remainingSpots = 2,
            rating = 4.8,

            organizer = Organizer(
                id = "org_1",
                name = "Carlos Mendoza",
                username = "@carlosm",
                city = "Bogotá",
                rating = 4.9,
                isVerified = true,
                attendancePercentage = 96
            ),

            participants = listOf(
                Participant(
                    id = "p1",
                    name = "Carlos Mendoza",
                    initials = "CM",
                    rating = 4.9,
                    isVerified = true,
                    isOrganizer = true
                ),
                Participant(
                    id = "p2",
                    name = "Juan López",
                    initials = "JL",
                    rating = 4.7,
                    isVerified = true
                ),
                Participant(
                    id = "p3",
                    name = "Paula Arias",
                    initials = "PA",
                    rating = 4.8,
                    isVerified = false
                ),
                Participant(
                    id = "p4",
                    name = "David Vargas",
                    initials = "DV",
                    rating = 4.6,
                    isVerified = true
                ),
                Participant(
                    id = "p5",
                    name = "Sara Nieto",
                    initials = "SN",
                    rating = 4.7,
                    isVerified = false
                )
            )
        )
    }
}