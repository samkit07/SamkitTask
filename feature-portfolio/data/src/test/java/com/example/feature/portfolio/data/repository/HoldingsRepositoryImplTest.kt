package com.example.feature.portfolio.data.repository

import com.example.core.common.Result
import com.example.feature.portfolio.data.mapper.toDomain
import com.example.feature.portfolio.data.remote.HoldingsApi
import com.example.feature.portfolio.data.remote.HoldingsDataDto
import com.example.feature.portfolio.data.remote.HoldingsDto
import com.example.feature.portfolio.data.remote.HoldingsResponseDto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class HoldingsRepositoryImplTest {

    private var mockHoldingsApi: HoldingsApi = mockk()
    private lateinit var systemUnderTest: HoldingsRepositoryImpl

    @Before
    fun setUp() {
        systemUnderTest = HoldingsRepositoryImpl(mockHoldingsApi)
    }

    @Test
    fun `getHoldings returns success when api call is successful`() = runTest {
        val remoteHoldings = listOf(
            HoldingsDto(
                symbol = "BTC",
                quantity = 2,
                ltp = 50000.0,
                averagePrice = 45000.0,
                close = 48000.0
            )
        )
        val response = HoldingsResponseDto(HoldingsDataDto(remoteHoldings))
        coEvery { mockHoldingsApi.getHoldings() } returns response

        val expectedHoldings = remoteHoldings.map { it.toDomain() }

        val result = systemUnderTest.getHoldings()

        assertTrue(result is Result.Success)
        assertEquals(expectedHoldings, (result as Result.Success).data)
    }

    @Test
    fun `getHoldings returns error when api call fails`() = runTest {
        val exception = IOException("Network error")
        coEvery { mockHoldingsApi.getHoldings() } throws exception

        val result = systemUnderTest.getHoldings()

        assertTrue(result is Result.Error)
        assertEquals(exception, (result as Result.Error).throwable)
    }
}
