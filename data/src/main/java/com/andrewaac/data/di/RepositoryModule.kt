package com.andrewaac.data.di

import com.andrewaac.data.api.CreditScoreApi
import com.andrewaac.data.repository.CreditScoreRepositoryImpl
import com.andrewaac.donutchallenge.repository.CreditScoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun providesCreditScoreRepository(creditScoreApi: CreditScoreApi): CreditScoreRepository =
        CreditScoreRepositoryImpl(creditScoreApi)
}
