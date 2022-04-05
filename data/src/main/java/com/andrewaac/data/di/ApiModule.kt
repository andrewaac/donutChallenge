package com.andrewaac.data.di

import com.andrewaac.data.api.CreditScoreApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    private val baseUrl = "https://android-interview.s3.eu-west-2.amazonaws.com/"

    @Singleton
    @Provides
    fun providesRetrofitClient(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().setPrettyPrinting().create()
            )
        )
        .build()

    @Singleton
    @Provides
    fun providesGetCreditScoreApi(retrofit: Retrofit): CreditScoreApi =
        retrofit.create(CreditScoreApi::class.java)
}
