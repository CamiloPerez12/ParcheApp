package com.jcpd.feature_home.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jcpd.core_ui.components.ParcheCard
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.feature_home.presentation.home.HomeQuickStatUi

@Composable
fun HomeQuickStatsRow(
    stats: List<HomeQuickStatUi>,
    modifier: Modifier = Modifier
) {
    val spacing = ParcheThemeTokens.spacing

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing.md)
    ) {
        stats.take(3).forEach { stat ->
            ParcheCard(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stat.value,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = stat.label,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = spacing.xs)
                )
            }
        }
    }
}