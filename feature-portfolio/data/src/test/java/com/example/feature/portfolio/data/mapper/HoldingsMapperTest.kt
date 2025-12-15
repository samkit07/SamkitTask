package com.example.feature.portfolio.data.mapper

import com.example.feature.portfolio.data.remote.HoldingsDto
import com.example.feature.portfolio.domain.model.Holding
import org.junit.Assert.assertEquals
import org.junit.Test

class HoldingsMapperTest {

    @Test
    fun `toDomain should correctly map HoldingsDto to Holding domain model`() {
        val holdingsDto = HoldingsDto(
            symbol = "BTC",
            quantity = 2,
            ltp = 50000.0,
            averagePrice = 45000.0,
            close = 48000.0
        )

        val result = holdingsDto.toDomain()

        val expected = Holding(
            symbol = "BTC",
            quantity = 2,
            lastTradedPrice = 50000.0,
            averagePrice = 45000.0,
            closePrice = 48000.0
        )
        assertEquals(expected, result)
    }
}
