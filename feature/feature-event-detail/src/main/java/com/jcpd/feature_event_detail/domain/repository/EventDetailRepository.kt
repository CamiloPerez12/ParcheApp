package com.jcpd.feature_event_detail.domain.repository

import com.jcpd.feature_event_detail.domain.model.EventDetail

interface EventDetailRepository {
    suspend fun getEventDetail(eventId: String): EventDetail
}