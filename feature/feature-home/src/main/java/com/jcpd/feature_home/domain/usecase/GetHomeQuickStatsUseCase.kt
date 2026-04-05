package com.jcpd.feature_home.domain.usecase

import com.jcpd.feature_home.domain.model.HomeQuickStat
import com.jcpd.feature_home.domain.repository.HomeRepository

class GetHomeQuickStatsUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): List<HomeQuickStat> {
        return repository.getQuickStats()
    }
}