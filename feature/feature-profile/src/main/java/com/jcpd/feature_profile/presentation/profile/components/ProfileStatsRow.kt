package com.jcpd.feature_profile.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jcpd.core_ui.components.ParcheCard
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.TextMuted
import com.jcpd.core_ui.theme.TextPrimary

@Composable
fun ProfileStatsRow(
    rating: String,
    eventsJoined: String,
    reliability: String,
    modifier: Modifier = Modifier
) {
    val spacing = ParcheThemeTokens.spacing

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing.md)
    ) {
        ProfileStatItem(
            value = rating,
            label = "Rating",
            modifier = Modifier.weight(1f)
        )
        ProfileStatItem(
            value = eventsJoined,
            label = "Eventos",
            modifier = Modifier.weight(1f)
        )
        ProfileStatItem(
            value = reliability,
            label = "Confianza",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun ProfileStatItem(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    ParcheCard(modifier = modifier) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            color = TextPrimary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = TextMuted
        )
    }
}