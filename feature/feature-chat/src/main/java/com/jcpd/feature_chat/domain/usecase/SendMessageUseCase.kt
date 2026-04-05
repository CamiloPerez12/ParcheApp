package com.jcpd.feature_chat.domain.usecase

import com.jcpd.feature_chat.domain.repository.ChatRepository

class SendMessageUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(eventId: String, message: String) {
        repository.sendMessage(eventId, message)
    }
}