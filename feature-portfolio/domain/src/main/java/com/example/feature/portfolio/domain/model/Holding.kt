package com.example.feature.portfolio.domain.model

data class Holding(
    val symbol: String,
    val quantity: Int,
    val lastTradedPrice: Double,
    val averagePrice: Double,
    val closePrice: Double,
){
    fun getTotalProfitLoss(): Double {
        return (lastTradedPrice - averagePrice) * quantity
    }
}