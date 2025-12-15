package com.example.feature.portfolio.presentation.state

import com.example.feature.portfolio.domain.model.Holding
import com.example.feature.portfolio.domain.model.PLData

data class HoldingsUiState(
    val isLoading: Boolean = false,
    val holdings: List<Holding> = emptyList(),
    val portfolioSummary: PLData? = null,
    val error: String? = null,
    val isExpanded: Boolean = false
)