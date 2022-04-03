package com.andrewaac.donutchallenge.repository

import com.andrewaac.donutchallenge.model.CreditScore

interface CreditScoreRepository {
    suspend fun getCreditScore(): CreditScore
}
