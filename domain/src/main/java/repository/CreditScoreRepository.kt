package repository

import model.CreditScore

interface CreditScoreRepository {
    suspend fun getCreditScore(): CreditScore
}
