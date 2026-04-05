package com.jcpd.feature_profile.data.repository

import com.jcpd.feature_profile.domain.model.UserProfile
import com.jcpd.feature_profile.domain.repository.ProfileRepository

class FakeProfileRepository : ProfileRepository {
    override suspend fun getProfile(): UserProfile {
        return UserProfile(
            id = "user_1",
            fullName = "Camilo Pérez",
            username = "@camiloperez",
            city = "Bogotá",
            bio = "Me gustan los partidos casuales, el fútbol 5 y conocer gente para hacer parche los fines de semana.",
            rating = 4.8,
            eventsJoined = 24,
            reliability = 92,
            preferredSports = listOf("Fútbol", "Tenis", "Basket")
        )
    }
}