package com.example.feature.portfolio.data.remote

import retrofit2.http.GET

interface HoldingsApi {
    @GET("/")
    suspend fun getHoldings(): HoldingsResponseDto
}