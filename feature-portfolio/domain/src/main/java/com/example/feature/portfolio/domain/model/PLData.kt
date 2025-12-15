package com.example.feature.portfolio.domain.model

data class PLData(
    val currentValue: Double,
    val totalInvestment: Double,
    val todayPnL: Double,
    val totalPnL: Double,
    val pnlPercentage: Double
)
