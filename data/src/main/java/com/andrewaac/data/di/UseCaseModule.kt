package com.andrewaac.data.di

import com.andrewaac.donutchallenge.repository.CreditScoreRepository
import com.andrewaac.donutchallenge.usecase.GetCreditScoreUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class UseCaseModule {

    @Provides
    fun providesGetCreditScoreUseCase(
        creditScoreRepository: CreditScoreRepository
    ): GetCreditScoreUseCase = GetCreditScoreUseCase(creditScoreRepository)

}
