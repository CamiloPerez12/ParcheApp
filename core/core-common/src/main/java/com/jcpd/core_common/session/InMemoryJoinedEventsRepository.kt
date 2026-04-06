package com.jcpd.core_common.session

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class InMemoryJoinedEventsRepository : JoinedEventsRepository {

    private val _joinedEventIds = MutableStateFlow<Set<String>>(emptySet())
    override val joinedEventIds: StateFlow<Set<String>> = _joinedEventIds.asStateFlow()

    override fun isJoined(eventId: String): Boolean {
        return _joinedEventIds.value.contains(eventId)
    }

    override suspend fun joinEvent(eventId: String) {
        _joinedEventIds.value = _joinedEventIds.value + eventId
    }
}