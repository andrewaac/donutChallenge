package com.andrewaac.data.repository

import android.util.Log
import com.andrewaac.data.api.CreditScoreApi
import com.andrewaac.data.model.dto.toDomain
import com.andrewaac.donutchallenge.model.CreditScore
import com.andrewaac.donutchallenge.repository.CreditScoreRepository
import java.io.IOException

class CreditScoreRepositoryImpl(
    private val creditScoreApi: CreditScoreApi
) : CreditScoreRepository {

    @Throws(IOException::class)
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
            Log.e(javaClass.simpleName, null, e)
            CreditScore.EmptyCreditScore
        }
    }
}
