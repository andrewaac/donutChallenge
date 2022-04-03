package com.andrewaac.data.api

import com.andrewaac.data.model.dto.CreditScoreDTO
import retrofit2.Response
import retrofit2.http.GET

interface CreditScoreApi {

    @GET("endpoint.json")
    suspend fun getCreditScore(): Response<CreditScoreDTO>
}
