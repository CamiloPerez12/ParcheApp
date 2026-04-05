package com.jcpd.feature_chat.presentation.chat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jcpd.core_ui.theme.ParcheGreenLight
import com.jcpd.core_ui.theme.ParcheRadius
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.ParcheWhite
import com.jcpd.core_ui.theme.TextMuted
import com.jcpd.core_ui.theme.TextPrimary
import com.jcpd.feature_chat.domain.model.ChatMessage

@Composable
fun MessageBubble(
    message: ChatMessage,
    modifier: Modifier = Modifier
) {
    val spacing = ParcheThemeTokens.spacing
    val isMine = message.isMine

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = if (isMine) Alignment.End else Alignment.Start
    ) {
        Surface(
            modifier = Modifier.wrapContentWidth(),
            shape = RoundedCornerShape(ParcheRadius.Medium),
            color = if (isMine) ParcheGreenLight else ParcheWhite,
            tonalElevation = 1.dp,
            shadowElevation = 3.dp
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = spacing.md,
                    vertical = spacing.sm
                ),
                verticalArrangement = Arrangement.spacedBy(spacing.xs)
            ) {
                if (!isMine) {
                    Text(
                        text = message.userName,
                        style = MaterialTheme.typography.labelMedium,
                        color = TextMuted
                    )
                }

                Text(
                    text = message.message,
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextPrimary
                )

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = message.timestamp,
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                }
            }
        }
    }
}