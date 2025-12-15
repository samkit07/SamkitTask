package com.example.feature.portfolio.domain.repository

import com.example.core.common.Result
import com.example.feature.portfolio.domain.model.Holding

interface HoldingsRepository {
    suspend fun getHoldings(): Result<List<Holding>>
}