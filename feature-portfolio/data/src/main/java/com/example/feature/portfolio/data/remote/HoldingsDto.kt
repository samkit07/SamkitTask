package com.example.feature.portfolio.data.remote

import com.google.gson.annotations.SerializedName

data class HoldingsResponseDto(
    @SerializedName("data") val data: HoldingsDataDto,
)

data class HoldingsDataDto(
    @SerializedName("userHolding") val userHolding: List<HoldingsDto>,
)

data class HoldingsDto(
    @SerializedName("symbol") val symbol: String,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("ltp") val ltp: Double,
    @SerializedName("avgPrice") val averagePrice: Double,
    @SerializedName("close") val close: Double,
)
