package com.example.feature.portfolio.data.mapper

import com.example.feature.portfolio.data.remote.HoldingsDto
import com.example.feature.portfolio.domain.model.Holding

fun HoldingsDto.toDomain() = Holding(
    symbol = symbol,
    quantity = quantity,
    lastTradedPrice = ltp,
    averagePrice = averagePrice,
    closePrice = close,
)