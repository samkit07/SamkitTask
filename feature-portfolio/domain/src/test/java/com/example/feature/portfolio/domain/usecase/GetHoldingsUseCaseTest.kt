package com.example.feature.portfolio.domain.usecase

import com.example.core.common.Result
import com.example.feature.portfolio.domain.MockHolding
import com.example.feature.portfolio.domain.repository.HoldingsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test


@ExperimentalCoroutinesApi
class GetHoldingsUseCaseTest {
    private val mockRepository: HoldingsRepository = mockk()
    private val systemUnderTest: GetHoldingsUseCase = GetHoldingsUseCase(mockRepository)

    @Test
    fun `invoke returns success when repository returns success`() = runTest {
        val holdings = MockHolding.holdings
        coEvery { mockRepository.getHoldings() } returns Result.Success(holdings)

        val result = systemUnderTest()

        assert(result is Result.Success)
        assertEquals(holdings, (result as Result.Success).data)
    }

    @Test
    fun `invoke returns error when repository returns error`() = runTest {
        val exception = RuntimeException("Network Error")
        coEvery { mockRepository.getHoldings() } returns Result.Error(exception)

        val result = systemUnderTest()

        assert(result is Result.Error)
        assertEquals(exception, (result as Result.Error).throwable)
    }
}
