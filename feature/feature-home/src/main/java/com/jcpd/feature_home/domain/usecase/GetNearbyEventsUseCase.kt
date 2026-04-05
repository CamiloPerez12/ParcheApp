package com.jcpd.feature_home.domain.usecase

import com.jcpd.feature_home.domain.model.HomeEvent
import com.jcpd.feature_home.domain.repository.HomeRepository

class GetNearbyEventsUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): List<HomeEvent> {
        return repository.getNearbyEvents()
    }
}