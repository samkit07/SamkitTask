package com.example.feature.portfolio.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.feature.portfolio.presentation.viewmodel.PortfolioViewModel

@Composable
fun PortfolioScreen(viewModel: PortfolioViewModel = hiltViewModel<PortfolioViewModel>()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            PortfolioTopBar()
        },
        bottomBar = {
            val summary = uiState.portfolioSummary
            if (summary != null) {
                PortfolioSummaryScreen(
                    portfolioSummary = summary,
                    isExpanded = uiState.isExpanded,
                    onToggleExpanded = viewModel::toggleExpanded
                )
            }
        }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Box(modifier = Modifier.fillMaxSize()) {
                when {
                    uiState.isLoading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    uiState.error != null -> {
                        ErrorScreen(errorText = uiState.error ?: "", onRetry = viewModel::retry)
                    }

                    else -> {
                        HoldingContent(
                            holdings = uiState.holdings,
                        )
                    }
                }
            }
        }

    }
}