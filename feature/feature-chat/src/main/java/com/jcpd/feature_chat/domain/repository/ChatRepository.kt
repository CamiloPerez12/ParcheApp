package com.jcpd.feature_chat.domain.repository

import com.jcpd.feature_chat.domain.model.ChatMessage

interface ChatRepository {
    suspend fun getMessages(eventId: String): List<ChatMessage>
    suspend fun sendMessage(eventId: String, message: String)
}