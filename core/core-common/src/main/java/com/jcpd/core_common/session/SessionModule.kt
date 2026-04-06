package com.jcpd.core_common.session

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SessionModule {

    @Provides
    @Singleton
    fun provideJoinedEventsRepository(): JoinedEventsRepository {
        return InMemoryJoinedEventsRepository()
    }
}