package com.andrewaac.data.repository

import com.andrewaac.data.api.CreditScoreApi
import com.andrewaac.data.model.dto.toDomain
import com.andrewaac.donutchallenge.model.CreditScore
import com.andrewaac.donutchallenge.repository.CreditScoreRepository
import java.io.IOException
import javax.inject.Inject

class CreditScoreRepositoryImpl @Inject constructor(
    private val creditScoreApi: CreditScoreApi
) : CreditScoreRepository {

    override suspend fun getCreditScore(): CreditScore {
        return try {
            val response = creditScoreApi.getCreditScore()

            if (response.isSuccessful) {
                val creditScoreDTO = response.body()
                creditScoreDTO.toDomain()
            } else {
                CreditScore.EmptyCreditScore
            }
        } catch (e: IOException) {
            CreditScore.EmptyCreditScore
        }
    }
}
