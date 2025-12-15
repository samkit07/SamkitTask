package com.example.feature.portfolio.domain

import com.example.feature.portfolio.domain.model.Holding

object MockHolding {
    val holdings = listOf(
        Holding(
            symbol = "BTC",
            quantity = 10,
            lastTradedPrice = 50000.0,
            averagePrice = 50000.0,
            closePrice = 40000.0
        )
    )

}