package com.jcpd.feature_profile.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jcpd.core_ui.components.ParcheCard
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.TextMuted
import com.jcpd.core_ui.theme.TextPrimary

@Composable
fun ProfileInfoCard(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    val spacing = ParcheThemeTokens.spacing

    ParcheCard(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(spacing.sm)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = TextPrimary
            )

            Text(
                text = content,
                style = MaterialTheme.typography.bodyLarge,
                color = TextMuted
            )
        }
    }
}