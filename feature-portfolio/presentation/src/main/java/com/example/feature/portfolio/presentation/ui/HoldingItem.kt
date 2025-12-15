package com.example.feature.portfolio.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.common.toRupees
import com.example.feature.portfolio.domain.model.Holding

@Composable
fun HoldingItem(holding: Holding) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = holding.symbol.uppercase(), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "NET QTY: ${holding.quantity}", fontSize = 14.sp, color = Color.Gray)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(text = "LTP: ${holding.lastTradedPrice.toRupees()}", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(text = "P&L: ", fontSize = 14.sp, color = Color.Gray)
                Text(
                    text = holding.getTotalProfitLoss().toRupees(),
                    fontSize = 14.sp,
                    color = if (holding.getTotalProfitLoss() >= 0) Color(0xFF00C853) else Color.Red
                )
            }
        }
    }
}