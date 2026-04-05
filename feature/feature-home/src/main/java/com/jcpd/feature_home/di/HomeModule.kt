package com.jcpd.feature_home.di

import com.jcpd.feature_home.data.repository.FakeHomeRepository
import com.jcpd.feature_home.domain.repository.HomeRepository
import com.jcpd.feature_home.domain.usecase.GetHomeQuickStatsUseCase
import com.jcpd.feature_home.domain.usecase.GetNearbyEventsUseCase
import com.jcpd.feature_home.domain.usecase.GetSelectedLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeRepository(): HomeRepository {
        return FakeHomeRepository()
    }

    @Provides
    @Singleton
    fun provideGetNearbyEventsUseCase(
        repository: HomeRepository
    ): GetNearbyEventsUseCase {
        return GetNearbyEventsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetHomeQuickStatsUseCase(
        repository: HomeRepository
    ): GetHomeQuickStatsUseCase {
        return GetHomeQuickStatsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSelectedLocationUseCase(
        repository: HomeRepository
    ): GetSelectedLocationUseCase {
        return GetSelectedLocationUseCase(repository)
    }
}