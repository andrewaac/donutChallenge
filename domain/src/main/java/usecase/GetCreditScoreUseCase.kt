package usecase

import repository.CreditScoreRepository

class GetCreditScoreUseCase(private val creditScoreRepository: CreditScoreRepository) {
    suspend fun getCreditScore() = creditScoreRepository.getCreditScore()
}
