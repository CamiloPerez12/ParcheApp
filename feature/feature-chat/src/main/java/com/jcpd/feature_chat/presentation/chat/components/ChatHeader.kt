package com.jcpd.feature_chat.presentation.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jcpd.core_ui.theme.Gray200
import com.jcpd.core_ui.theme.ParcheChatHeaderOverlay
import com.jcpd.core_ui.theme.ParcheRadius
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.TextPrimary
import com.jcpd.core_ui.theme.TextSecondary

@Composable
fun ChatHeader(
    title: String,
    subtitle: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = ParcheThemeTokens.spacing

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(ParcheChatHeaderOverlay.copy(alpha = 0.96f))
            .border(
                width = 1f.dp,
                color = Gray200,
                shape = RoundedCornerShape(
                    bottomStart = ParcheRadius.Large,
                    bottomEnd = ParcheRadius.Large
                )
            )
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(
                horizontal = spacing.md,
                vertical = spacing.md
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.sm)
    ) {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = null,
                tint = TextPrimary
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(spacing.xs)
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