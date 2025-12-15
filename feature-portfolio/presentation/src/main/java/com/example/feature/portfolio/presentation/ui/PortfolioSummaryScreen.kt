package com.example.feature.portfolio.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.common.toRupees
import com.example.feature.portfolio.domain.model.PLData
import com.example.feature.portfolio.presentation.R
import java.util.Locale
import kotlin.math.abs

@Composable
fun PortfolioSummaryScreen(
    portfolioSummary: PLData,
    isExpanded: Boolean,
    onToggleExpanded: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 8.dp,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable { onToggleExpanded() }
                .navigationBarsPadding()
                .padding(16.dp)
        ) {
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(animationSpec = tween(300)),
                exit = shrinkVertically(animationSpec = tween(300)),
            ) {
                Column {
                    SummaryRow(
                        title = "Current value*",
                        value = portfolioSummary.currentValue.toRupees()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    SummaryRow(
                        title = "Total investment*",
                        value = portfolioSummary.totalInvestment.toRupees()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    SummaryRow(
                        title = "Today's Profit & Loss*",
                        value = portfolioSummary.todayPnL.toRupees(),
                        valueColor = if (portfolioSummary.todayPnL >= 0) Color(0xFF00C853) else Color.Red
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Profit & Loss*", fontWeight = FontWeight.SemiBold)
                Icon(
                    painter = painterResource(if (isExpanded) R.drawable.ic_arrow_down else R.drawable.ic_arrow_up),
                    contentDescription = "Expand/Collapse"
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    color = if (portfolioSummary.totalPnL >= 0) Color(0xFF00C853) else Color.Red,
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        ) {
                            append(portfolioSummary.totalPnL.toRupees())
                        }
                        append(" ")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )
                        ) {
                            append(
                                "(${
                                    String.format(
                                        Locale.US,
                                        "%.2f",
                                        abs(portfolioSummary.pnlPercentage)
                                    )
                                }%) "
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun SummaryRow(title: String, value: String, valueColor: Color = Color.Unspecified) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = title, color = Color.Gray, fontSize = 14.sp)
        Text(text = value, fontWeight = FontWeight.SemiBold, color = valueColor, fontSize = 14.sp)
    }
}
