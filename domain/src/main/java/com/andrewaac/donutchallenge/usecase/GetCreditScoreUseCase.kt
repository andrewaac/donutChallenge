package com.andrewaac.donutchallenge.usecase

import com.andrewaac.donutchallenge.repository.CreditScoreRepository

class GetCreditScoreUseCase(private val creditScoreRepository: CreditScoreRepository) {
    suspend fun getCreditScore() = creditScoreRepository.getCreditScore()
}
