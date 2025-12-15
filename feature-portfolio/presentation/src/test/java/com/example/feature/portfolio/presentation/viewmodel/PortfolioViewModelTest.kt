
package com.example.feature.portfolio.presentation.viewmodel

import com.example.core.common.Result
import com.example.feature.portfolio.domain.model.Holding
import com.example.feature.portfolio.domain.model.PLData
import com.example.feature.portfolio.domain.usecase.GetHoldingsUseCase
import com.example.feature.portfolio.domain.usecase.GetPortfolioSummaryUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PortfolioViewModelTest {
    private val mockGetHoldingsUseCase: GetHoldingsUseCase = mockk()
    private val mockGetPortfolioSummaryUseCase: GetPortfolioSummaryUseCase = mockk()
    private lateinit var systemUnderTest: PortfolioViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test fetchHoldings success`() = runTest {
        val holdings = listOf(Holding("BTC", 10, 50000.0, 50000.0, 40000.0))
        val portfolioSummary = PLData(50000.0, 50000.0, 0.0, 0.0, 22.5)
        coEvery { mockGetHoldingsUseCase() } returns Result.Success(holdings)
        every { mockGetPortfolioSummaryUseCase(holdings) } returns portfolioSummary

        systemUnderTest = PortfolioViewModel(mockGetHoldingsUseCase, mockGetPortfolioSummaryUseCase)

        val uiState = systemUnderTest.uiState.value
        assertEquals(holdings, uiState.holdings)
        assertEquals(portfolioSummary, uiState.portfolioSummary)
        assertEquals(false, uiState.isLoading)
        assertEquals(null, uiState.error)
    }

    @Test
    fun `test fetchHoldings error`() = runTest {
        val errorMessage = "Network Error"
        coEvery { mockGetHoldingsUseCase() } returns Result.Error(Throwable(errorMessage))

        systemUnderTest = PortfolioViewModel(mockGetHoldingsUseCase, mockGetPortfolioSummaryUseCase)

        val uiState = systemUnderTest.uiState.value
        assertEquals(emptyList<Holding>(), uiState.holdings)
        assertEquals(null, uiState.portfolioSummary)
        assertEquals(false, uiState.isLoading)
        assertEquals(errorMessage, uiState.error)
    }

    @Test
    fun `test toggleExpanded`() = runTest {
        coEvery { mockGetHoldingsUseCase() } returns Result.Success(emptyList())
        every { mockGetPortfolioSummaryUseCase(any()) } returns PLData(0.0, 0.0, 0.0, 0.0, 0.0)
        systemUnderTest = PortfolioViewModel(mockGetHoldingsUseCase, mockGetPortfolioSummaryUseCase)
        val initialValue = systemUnderTest.uiState.value.isExpanded

        systemUnderTest.toggleExpanded()

        assertEquals(initialValue.not(), systemUnderTest.uiState.value.isExpanded)

        systemUnderTest.toggleExpanded()

        assertEquals(initialValue, systemUnderTest.uiState.value.isExpanded)
    }
}
