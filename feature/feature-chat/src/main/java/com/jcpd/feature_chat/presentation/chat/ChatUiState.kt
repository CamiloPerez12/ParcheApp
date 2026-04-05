package com.jcpd.feature_chat.presentation.chat

import com.jcpd.feature_chat.domain.model.ChatMessage

data class ChatUiState(
    val isLoading: Boolean = false,
    val isSending: Boolean = false,
    val messages: List<ChatMessage> = emptyList(),
    val inputText: String = "",
    val eventTitle: String = "Fútbol 5 en Galerías",
    val eventSubtitle: String = "Hoy • 7:00 PM • Cancha El Parque",
    val errorMessage: String? = null
)