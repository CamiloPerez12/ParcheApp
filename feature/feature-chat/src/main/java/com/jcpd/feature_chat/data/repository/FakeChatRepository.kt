package com.jcpd.feature_chat.data.repository

import com.jcpd.feature_chat.domain.model.ChatMessage
import com.jcpd.feature_chat.domain.repository.ChatRepository

class FakeChatRepository : ChatRepository {

    private val messages = mutableListOf(
        ChatMessage("1", "Carlos", "Llegan a las 7?", "6:10 PM", false),
        ChatMessage("2", "Yo", "Sí, estoy en camino", "6:12 PM", true),
        ChatMessage("3", "Juan", "Falta 1 jugador", "6:15 PM", false)
    )

    override suspend fun getMessages(eventId: String): List<ChatMessage> {
        return messages
    }

    override suspend fun sendMessage(eventId: String, message: String) {
        messages.add(
            ChatMessage(
                id = System.currentTimeMillis().toString(),
                userName = "Yo",
                message = message,
                timestamp = "Ahora",
                isMine = true
            )
        )
    }
}