package com.jcpd.feature_profile.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jcpd.core_ui.components.ParcheAvatar
import com.jcpd.core_ui.components.ParcheAvatarSize
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.TextMuted
import com.jcpd.core_ui.theme.TextPrimary

@Composable
fun ProfileHeader(
    fullName: String,
    username: String,
    city: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = ParcheThemeTokens.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = spacing.lg, vertical = spacing.lg),
        verticalArrangement = Arrangement.spacedBy(spacing.lg)
    ) {
        Row(
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

            Text(
                text = "Perfil",
                style = MaterialTheme.typography.headlineSmall,
                color = TextPrimary
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.md),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ParcheAvatar(
                initials = fullName.toInitials(),
                size = ParcheAvatarSize.Large
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.xs)
            ) {
                Text(
                    text = fullName,
                    style = MaterialTheme.typography.headlineSmall,
                    color = TextPrimary
                )

                Text(
                    text = username,
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextMuted
                )

                Text(
                    text = city,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextMuted
                )
            }
        }
    }
}

private fun String.toInitials(): String {
    val parts = trim().split(" ").filter { it.isNotBlank() }
    return when {
        parts.isEmpty() -> "?"
        parts.size == 1 -> parts.first().take(2).uppercase()
        else -> "${parts[0].first()}${parts[1].first()}".uppercase()
    }
}