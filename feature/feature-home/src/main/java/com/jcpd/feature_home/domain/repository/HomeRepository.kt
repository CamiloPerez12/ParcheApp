package com.jcpd.feature_home.domain.repository

import com.jcpd.feature_home.domain.model.HomeEvent
import com.jcpd.feature_home.domain.model.HomeQuickStat

interface HomeRepository {
    suspend fun getNearbyEvents(): List<HomeEvent>
    suspend fun getQuickStats(): List<HomeQuickStat>
    suspend fun getSelectedLocation(): String
}