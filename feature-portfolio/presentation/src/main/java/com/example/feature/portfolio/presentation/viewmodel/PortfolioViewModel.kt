package com.example.feature.portfolio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.common.Result.*
import com.example.feature.portfolio.domain.usecase.GetHoldingsUseCase
import com.example.feature.portfolio.domain.usecase.GetPortfolioSummaryUseCase
import com.example.feature.portfolio.presentation.state.HoldingsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val getHoldingsUseCase: GetHoldingsUseCase,
    private val getPortfolioSummaryUseCase: GetPortfolioSummaryUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(HoldingsUiState())
    val uiState: StateFlow<HoldingsUiState> = _uiState

    init {
        fetchHoldings()
    }

    private fun fetchHoldings() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            when (val holdings = getHoldingsUseCase()) {
                is Success -> {
                    val portfolioSummary = getPortfolioSummaryUseCase(holdings.data)
                    _uiState.update {
                        it.copy(
                            holdings = holdings.data,
                            portfolioSummary = portfolioSummary,
                            isLoading = false
                        )
                    }
                }
                is Error -> {
                    _uiState.update {
                        it.copy(
                            error = holdings.throwable.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun toggleExpanded() {
        _uiState.update { it.copy(isExpanded = !it.isExpanded) }
    }

    fun retry(){
        fetchHoldings()
    }

}