package com.example.feature.portfolio.data.di

import com.example.feature.portfolio.data.remote.HoldingsApi
import com.example.feature.portfolio.data.repository.HoldingsRepositoryImpl
import com.example.feature.portfolio.domain.repository.HoldingsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindHoldingsRepository(
        holdingsRepositoryImpl: HoldingsRepositoryImpl
    ): HoldingsRepository

    companion object {
        @Provides
        fun provideHoldingsApiService(retrofit: Retrofit): HoldingsApi {
            return retrofit.create(HoldingsApi::class.java)
        }
    }
}