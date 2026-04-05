package com.jcpd.feature_chat.data.repository

import com.jcpd.feature_chat.domain.model.ChatMessage
import com.jcpd.feature_chat.domain.model.MessageType
import com.jcpd.feature_chat.domain.repository.ChatRepository

class FakeChatRepository : ChatRepository {

    private val messagesByEvent = mutableMapOf(
        "event_1" to mutableListOf(
            ChatMessage("sys_1", "", "Carlos se unió al evento", "6:05 PM", false, MessageType.SYSTEM),
            ChatMessage("1", "Carlos", "¿Llegan a las 7?", "6:10 PM", false),
            ChatMessage("2", "Yo", "Sí, estoy en camino", "6:12 PM", true),
            ChatMessage("sys_2", "", "Quedan 2 cupos disponibles", "6:13 PM", false, MessageType.SYSTEM),
            ChatMessage("3", "Juan", "Falta 1 jugador", "6:15 PM", false)
        ),
        "event_2" to mutableListOf(
            ChatMessage("4", "Laura", "Yo llevo pelotas", "5:40 PM", false),
            ChatMessage("5", "Yo", "Perfecto, yo llevo agua", "5:42 PM", true),
            ChatMessage("6", "Mateo", "Nos vemos en la entrada del club", "5:45 PM", false),
            ChatMessage("sys_3", "", "Laura creó el evento", "5:30 PM", false, MessageType.SYSTEM),
        ),
        "event_3" to mutableListOf(
            ChatMessage("7", "Andrés", "Ya estamos completos", "7:05 PM", false),
            ChatMessage("8", "Yo", "Buenísimo", "7:06 PM", true),
            ChatMessage("9", "Santi", "Empiecen calentando", "7:07 PM", false),
            ChatMessage("sys_4", "", "El partido empieza en 10 minutos", "6:50 PM", false, MessageType.SYSTEM),
        ),
        "event_4" to mutableListOf(
            ChatMessage("10", "Sara", "¿En qué parte del parque nos vemos?", "8:10 PM", false),
            ChatMessage("11", "Yo", "Cerca a la entrada principal", "8:11 PM", true),
            ChatMessage("12", "Miguel", "Voy 10 minutos tarde", "8:12 PM", false),
            ChatMessage("sys_5", "", "Miguel se unió al evento", "8:05 PM", false, MessageType.SYSTEM),
        )
    )

    override suspend fun getMessages(eventId: String): List<ChatMessage> {
        return messagesByEvent[eventId]?.toList()
            ?: mutableListOf(
                ChatMessage(
                    id = "default_1",
                    userName = "Sistema",
                    message = "Bienvenidos al chat del evento",
                    timestamp = "Ahora",
                    isMine = false
                )
            )
    }

    override suspend fun sendMessage(eventId: String, message: String) {
        val currentMessages = messagesByEvent.getOrPut(eventId) { mutableListOf() }

        currentMessages.add(
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