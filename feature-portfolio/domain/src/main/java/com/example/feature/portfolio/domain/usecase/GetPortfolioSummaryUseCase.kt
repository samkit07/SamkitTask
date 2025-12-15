package com.example.feature.portfolio.domain.usecase

import android.util.Log
import com.example.feature.portfolio.domain.model.Holding
import com.example.feature.portfolio.domain.model.PLData
import javax.inject.Inject

class GetPortfolioSummaryUseCase @Inject constructor() {
    operator fun invoke(holdings: List<Holding>): PLData {
        val currentValue = holdings.sumOf { it.lastTradedPrice * it.quantity }
        val totalInvestment = holdings.sumOf { it.averagePrice * it.quantity }
        val todayPnl = holdings.sumOf { (it.closePrice - it.lastTradedPrice) * it.quantity }
        val totalPnl = currentValue - totalInvestment
        val pnlPercentage = if (totalInvestment != 0.0) (totalPnl / totalInvestment) * 100 else 0.0

        return PLData(
            currentValue = currentValue,
            totalInvestment = totalInvestment,
            todayPnL = todayPnl,
            totalPnL = totalPnl,
            pnlPercentage = pnlPercentage
        )
    }
}