package com.jcpd.feature_event_detail.di

import com.jcpd.feature_event_detail.data.repository.FakeEventDetailRepository
import com.jcpd.feature_event_detail.domain.repository.EventDetailRepository
import com.jcpd.feature_event_detail.domain.usecase.GetEventDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EventDetailModule {

    @Provides
    @Singleton
    fun provideEventDetailRepository(): EventDetailRepository {
        return FakeEventDetailRepository()
    }

    @Provides
    @Singleton
    fun provideGetEventDetailUseCase(
        repository: EventDetailRepository
    ): GetEventDetailUseCase {
        return GetEventDetailUseCase(repository)
    }
}