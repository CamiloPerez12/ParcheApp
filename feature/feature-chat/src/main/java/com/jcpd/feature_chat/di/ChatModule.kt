package com.jcpd.feature_chat.di

import com.jcpd.feature_chat.data.repository.FakeChatRepository
import com.jcpd.feature_chat.domain.repository.ChatRepository
import com.jcpd.feature_chat.domain.usecase.GetMessagesUseCase
import com.jcpd.feature_chat.domain.usecase.SendMessageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatModule {

    @Provides
    @Singleton
    fun provideChatRepository(): ChatRepository = FakeChatRepository()

    @Provides
    fun provideGetMessagesUseCase(repo: ChatRepository) =
        GetMessagesUseCase(repo)

    @Provides
    fun provideSendMessageUseCase(repo: ChatRepository) =
        SendMessageUseCase(repo)
}