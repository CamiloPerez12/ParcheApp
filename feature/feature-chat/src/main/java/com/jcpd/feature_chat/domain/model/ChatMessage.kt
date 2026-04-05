package com.jcpd.feature_chat.domain.model

data class ChatMessage(
    val id: String,
    val userName: String,
    val message: String,
    val timestamp: String,
    val isMine: Boolean,
    val type: MessageType = MessageType.USER
)

enum class MessageType {
    USER,
    SYSTEM
}