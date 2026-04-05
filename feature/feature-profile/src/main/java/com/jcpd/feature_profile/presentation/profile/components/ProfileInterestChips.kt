package com.jcpd.feature_profile.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jcpd.core_ui.components.ParcheCard
import com.jcpd.core_ui.components.ParcheChip
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.TextPrimary

@Composable
fun ProfileInterestChips(
    interests: List<String>,
    modifier: Modifier = Modifier
) {
    val spacing = ParcheThemeTokens.spacing

    ParcheCard(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Deportes favoritos",
            style = MaterialTheme.typography.headlineSmall,
            color = TextPrimary
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(spacing.sm),
            verticalArrangement = Arrangement.spacedBy(spacing.sm)
        ) {
            interests.forEach { interest ->
                ParcheChip(
                    text = interest,
                    selected = true,
                    onClick = {}
                )
            }
        }
    }
}