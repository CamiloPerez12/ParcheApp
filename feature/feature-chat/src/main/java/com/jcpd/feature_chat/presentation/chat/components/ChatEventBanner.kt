package com.jcpd.feature_chat.presentation.chat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jcpd.core_ui.components.ParcheCard
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.TextPrimary
import com.jcpd.core_ui.theme.TextSecondary

@Composable
fun ChatEventBanner(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    ParcheCard(modifier = modifier.fillMaxWidth()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(ParcheThemeTokens.spacing.sm)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = TextPrimary
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )
        }
    }
}