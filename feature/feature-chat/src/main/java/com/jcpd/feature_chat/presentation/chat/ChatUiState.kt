package com.jcpd.feature_chat.presentation.chat

import com.jcpd.feature_chat.domain.model.ChatMessage

data class ChatUiState(
    val isLoading: Boolean = false,
    val isSending: Boolean = false,
    val messages: List<ChatMessage> = emptyList(),
    val inputText: String = "",
    val eventTitle: String = "",
    val eventSubtitle: String = "",
    val errorMessage: String? = null,
    val hasAccess: Boolean = false,
)