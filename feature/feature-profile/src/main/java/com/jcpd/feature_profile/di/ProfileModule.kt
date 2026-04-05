package com.jcpd.feature_profile.di

import com.jcpd.feature_profile.data.repository.FakeProfileRepository
import com.jcpd.feature_profile.domain.repository.ProfileRepository
import com.jcpd.feature_profile.domain.usecase.GetProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    @Singleton
    fun provideProfileRepository(): ProfileRepository = FakeProfileRepository()

    @Provides
    fun provideGetProfileUseCase(
        repository: ProfileRepository
    ): GetProfileUseCase = GetProfileUseCase(repository)
}