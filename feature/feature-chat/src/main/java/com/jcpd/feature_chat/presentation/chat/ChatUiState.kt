package com.jcpd.feature_chat.presentation.chat

import com.jcpd.feature_chat.domain.model.ChatMessage

data class ChatUiState(
    val isLoading: Boolean = false,
    val messages: List<ChatMessage> = emptyList(),
    val inputText: String = ""
)