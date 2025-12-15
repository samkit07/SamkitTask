package com.example.feature.portfolio.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.feature.portfolio.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortfolioTopBar() {
    TopAppBar(
        title = { Text(text = "Portfolio") },
        navigationIcon = {
                Icon(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    painter = painterResource(R.drawable.ic_portfolio),
                    contentDescription = "Navigation Menu",
                    tint = Color.White
                )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF073763),
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
    )
}