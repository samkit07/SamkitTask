package com.example.feature.portfolio.domain.usecase

import com.example.feature.portfolio.domain.model.Holding
import org.junit.Assert.assertEquals
import org.junit.Test

class GetPortfolioSummaryUseCaseTest {

    private var systemUnderTest: GetPortfolioSummaryUseCase = GetPortfolioSummaryUseCase()

    @Test
    fun `invoke with empty holdings returns zeroed PLData`() {
        val holdings = emptyList<Holding>()

        val result = systemUnderTest(holdings)

        assertEquals(0.0, result.currentValue, 0.001)
        assertEquals(0.0, result.totalInvestment, 0.001)
        assertEquals(0.0, result.todayPnL, 0.001)
        assertEquals(0.0, result.totalPnL, 0.001)
        assertEquals(0.0, result.pnlPercentage, 0.001)
    }

    @Test
    fun `invoke with single holding calculates PLData correctly`() {
        val holdings = listOf(Holding("BTC", 2, 50000.0, 45000.0, 48000.0))

        val result = systemUnderTest(holdings)

        assertEquals(100000.0, result.currentValue, 0.001)
        assertEquals(90000.0, result.totalInvestment, 0.001)
        assertEquals(-4000.0, result.todayPnL, 0.001)
        assertEquals(10000.0, result.totalPnL, 0.001)
        assertEquals(11.111, result.pnlPercentage, 0.001)
    }

    @Test
    fun `invoke with multiple holdings calculates PLData correctly`() {
        val holdings = listOf(
            Holding("BTC", 2, 50000.0, 45000.0, 48000.0),
            Holding("ETH", 10, 2000.0, 1800.0, 2100.0)
        )

        val result = systemUnderTest(holdings)

        assertEquals(120000.0, result.currentValue, 0.001)
        assertEquals(108000.0, result.totalInvestment, 0.001)
        assertEquals(-3000.0, result.todayPnL, 0.001)
        assertEquals(12000.0, result.totalPnL, 0.001)
        assertEquals(11.111, result.pnlPercentage, 0.001)
    }

    @Test
    fun `invoke with zero total investment returns zero pnl percentage`() {
        val holdings = listOf(Holding("DOGE", 1000, 0.1, 0.0, 0.1))

        val result = systemUnderTest(holdings)

        assertEquals(100.0, result.currentValue, 0.001)
        assertEquals(0.0, result.totalInvestment, 0.001)
        assertEquals(0.0, result.todayPnL, 0.001)
        assertEquals(100.0, result.totalPnL, 0.001)
        assertEquals(0.0, result.pnlPercentage, 0.001)
    }
}
