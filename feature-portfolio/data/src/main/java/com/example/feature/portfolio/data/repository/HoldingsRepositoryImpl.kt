package com.example.feature.portfolio.data.repository

import com.example.core.common.Result
import com.example.feature.portfolio.data.mapper.toDomain
import com.example.feature.portfolio.data.remote.HoldingsApi
import com.example.feature.portfolio.domain.model.Holding
import com.example.feature.portfolio.domain.repository.HoldingsRepository
import javax.inject.Inject

class HoldingsRepositoryImpl @Inject constructor(
    private val holdingsApi: HoldingsApi
) : HoldingsRepository {

    override suspend fun getHoldings(): Result<List<Holding>> {
        return try {
            val response = holdingsApi.getHoldings()
            Result.Success(response.data.userHolding.map { it.toDomain() })
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}