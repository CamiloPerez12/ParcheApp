package com.jcpd.feature_event_detail.domain.usecase

import com.jcpd.feature_event_detail.domain.model.EventDetail
import com.jcpd.feature_event_detail.domain.repository.EventDetailRepository

class GetEventDetailUseCase(
    private val repository: EventDetailRepository
) {
    suspend operator fun invoke(eventId: String): EventDetail {
        return repository.getEventDetail(eventId)
    }
}