package com.example.feature.portfolio.domain.usecase

import com.example.core.common.Result
import com.example.feature.portfolio.domain.model.Holding
import com.example.feature.portfolio.domain.repository.HoldingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHoldingsUseCase @Inject constructor(
    private val repository: HoldingsRepository
) {
    suspend operator fun invoke(): Result<List<Holding>> {
        return repository.getHoldings()
    }
}