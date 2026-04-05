package com.jcpd.feature_chat.presentation.chat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.jcpd.core_ui.components.ParcheAvatar
import com.jcpd.core_ui.components.ParcheAvatarSize
import com.jcpd.core_ui.theme.Gray100
import com.jcpd.core_ui.theme.ParcheGreenLight
import com.jcpd.core_ui.theme.ParcheRadius
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.ParcheWhite
import com.jcpd.core_ui.theme.TextMuted
import com.jcpd.core_ui.theme.TextPrimary
import com.jcpd.feature_chat.domain.model.ChatMessage
import com.jcpd.feature_chat.domain.model.MessageType

@Composable
fun MessageBubble(
    message: ChatMessage,
    modifier: Modifier = Modifier
) {
    val spacing = ParcheThemeTokens.spacing

    when (message.type) {
        MessageType.SYSTEM -> {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = spacing.sm),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(ParcheRadius.Medium),
                    color = Gray100,
                    tonalElevation = 0.dp
                ) {
                    Text(
                        text = message.message,
                        style = MaterialTheme.typography.labelMedium,
                        color = TextMuted,
                        modifier = Modifier.padding(
                            horizontal = spacing.md,
                            vertical = spacing.sm
                        )
                    )
                }
            }
        }

        MessageType.USER -> {
            if (message.isMine) {
                MyMessageBubble(
                    message = message,
                    modifier = modifier
                )
            } else {
                OtherUserMessageBubble(
                    message = message,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
private fun MyMessageBubble(
    message: ChatMessage,
    modifier: Modifier = Modifier
) {
    val spacing = ParcheThemeTokens.spacing

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Surface(
            modifier = Modifier.wrapContentWidth(),
            shape = RoundedCornerShape(ParcheRadius.Medium),
            color = ParcheGreenLight,
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

@Composable
private fun OtherUserMessageBubble(
    message: ChatMessage,
    modifier: Modifier = Modifier
) {
    val spacing = ParcheThemeTokens.spacing

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
        verticalAlignment = Alignment.Bottom
    ) {
        ParcheAvatar(
            initials = message.userName.toInitials(),
            size = ParcheAvatarSize.Small
        )

        Surface(
            modifier = Modifier.wrapContentWidth(),
            shape = RoundedCornerShape(ParcheRadius.Medium),
            color = ParcheWhite,
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
                Text(
                    text = message.userName,
                    style = MaterialTheme.typography.labelMedium,
                    color = TextMuted
                )

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

private fun String.toInitials(): String {
    val parts = trim()
        .split(" ")
        .filter { it.isNotBlank() }

    return when {
        parts.isEmpty() -> "?"
        parts.size == 1 -> parts.first().take(2).uppercase()
        else -> "${parts[0].first()}${parts[1].first()}".uppercase()
    }
}