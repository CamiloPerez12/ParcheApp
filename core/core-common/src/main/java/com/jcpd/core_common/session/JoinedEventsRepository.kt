package com.jcpd.core_common.session

import kotlinx.coroutines.flow.StateFlow

interface JoinedEventsRepository {
    val joinedEventIds: StateFlow<Set<String>>

    fun isJoined(eventId: String): Boolean
    suspend fun joinEvent(eventId: String)
    suspend fun leaveEvent(eventId: String)
}