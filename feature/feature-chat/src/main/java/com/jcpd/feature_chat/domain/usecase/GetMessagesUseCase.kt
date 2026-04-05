package com.jcpd.feature_chat.domain.usecase

import com.jcpd.feature_chat.domain.repository.ChatRepository

class GetMessagesUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(eventId: String) =
        repository.getMessages(eventId)
}