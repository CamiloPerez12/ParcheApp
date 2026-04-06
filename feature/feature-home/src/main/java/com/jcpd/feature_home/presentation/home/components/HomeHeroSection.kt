package com.jcpd.feature_home.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.jcpd.core_ui.components.ParcheAvatar
import com.jcpd.core_ui.components.ParcheAvatarSize
import com.jcpd.core_ui.theme.ParcheGradients
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.ParcheWhite

@Composable
fun HomeHeroSection(
    greeting: String,
    title: String,
    locationLabel: String,
    unreadNotificationsCount: Int,
    notificationIcon: ImageVector,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = ParcheThemeTokens.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = ParcheGradients.BrandHero,
                shape = RoundedCornerShape(
                    bottomStart = spacing.xxxl,
                    bottomEnd = spacing.xxxl
                )
            )
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = spacing.lg, vertical = spacing.xl),
        verticalArrangement = Arrangement.spacedBy(spacing.lg)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Surface(
                shape = RoundedCornerShape(spacing.xl),
                color = ParcheWhite.copy(alpha = 0.18f)
            ) {
                Text(
                    text = locationLabel,
                    modifier = Modifier.padding(
                        horizontal = spacing.md,
                        vertical = spacing.sm
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = ParcheWhite
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    IconButton(
                        onClick = onNotificationClick,
                        modifier = Modifier.background(
                            color = ParcheWhite.copy(alpha = 0.18f),
                            shape = CircleShape
                        )
                    ) {
                        Icon(
                            imageVector = notificationIcon,
                            contentDescription = null,
                            tint = ParcheWhite
                        )
                    }

                    if (unreadNotificationsCount > 0) {
                        Badge(
                            modifier = Modifier.align(Alignment.TopEnd)
                        ) {
                            Text(
                                text = unreadNotificationsCount.coerceAtMost(99).toString()
                            )
                        }
                    }
                }

                IconButton(
                    onClick = onProfileClick,
                    modifier = Modifier.background(
                        color = ParcheWhite.copy(alpha = 0.18f),
                        shape = CircleShape
                    )
                ) {
                    ParcheAvatar(
                        initials = "CP",
                        size = ParcheAvatarSize.Small
                    )
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(spacing.sm)
        ) {
            Text(
                text = greeting,
                style = MaterialTheme.typography.bodyLarge,
                color = ParcheWhite.copy(alpha = 0.92f)
            )

            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = ParcheWhite
            )
        }
    }
}