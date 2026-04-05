package com.jcpd.core_ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsBasketball
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jcpd.core_ui.theme.Gray100
import com.jcpd.core_ui.theme.Gray200
import com.jcpd.core_ui.theme.ParcheGreenDark
import com.jcpd.core_ui.theme.ParcheGreenLight
import com.jcpd.core_ui.theme.ParcheRadius
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.ParcheWhite
import com.jcpd.core_ui.theme.TextMuted
import com.jcpd.core_ui.theme.TextPrimary
import com.jcpd.core_ui.theme.TextSecondary

enum class EventCardState {
    Default,
    Joined,
    Full
}

enum class EventSportType {
    Futbol,
    Tenis,
    Basket,
    Social
}

data class EventCardUiModel(
    val id: String,
    val title: String,
    val locationName: String,
    val distanceText: String,
    val dateText: String,
    val playersText: String,
    val levelText: String,
    val priceText: String? = null,
    val statusText: String,
    val statusType: ParcheStatusBadgeType,
    val ratingText: String,
    val spotsText: String,
    val ctaText: String,
    val state: EventCardState = EventCardState.Default,
    val sportType: EventSportType = EventSportType.Futbol,
    val participantInitials: List<String> = emptyList(),
    val onJoinClick: (() -> Unit)? = null,
    val onCardClick: (() -> Unit)? = null
)

@Composable
fun EventCard(
    event: EventCardUiModel,
    modifier: Modifier = Modifier
) {
    val spacing = ParcheThemeTokens.spacing
    val elevation = ParcheThemeTokens.elevation

    val ctaStyle = when (event.state) {
        EventCardState.Default -> ParcheButtonStyle.Primary
        EventCardState.Joined -> ParcheButtonStyle.Secondary
        EventCardState.Full -> ParcheButtonStyle.Outline
    }

    val ctaEnabled = event.state == EventCardState.Default

    Card(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (event.onCardClick != null) {
                    Modifier.clickable { event.onCardClick.invoke() }
                } else {
                    Modifier
                }
            ),
        shape = RoundedCornerShape(ParcheRadius.Large),
        colors = CardDefaults.cardColors(containerColor = ParcheWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation.card),
        border = if (event.state == EventCardState.Joined) {
            BorderStroke(1.dp, ParcheGreenLight)
        } else null
    ) {
        Column(
            modifier = Modifier.padding(spacing.lg),
            verticalArrangement = Arrangement.spacedBy(spacing.md)
        ) {
            HeaderRow(
                title = event.title,
                sportType = event.sportType,
                statusText = event.statusText,
                statusType = event.statusType
            )

            InfoRow(
                icon = Icons.Outlined.LocationOn,
                text = "${event.locationName} • ${event.distanceText}"
            )

            InfoRow(
                icon = Icons.Outlined.CalendarToday,
                text = event.dateText
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing.sm)
            ) {
                EventMetaPill(
                    icon = Icons.Outlined.People,
                    text = event.playersText
                )

                EventMetaPill(
                    icon = sportLevelIcon(event.sportType),
                    text = event.levelText
                )

                if (!event.priceText.isNullOrBlank()) {
                    EventMetaPill(
                        icon = Icons.Outlined.AttachMoney,
                        text = event.priceText
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RatingAndSpots(
                    ratingText = event.ratingText,
                    spotsText = event.spotsText
                )

                ParticipantsPreview(
                    initials = event.participantInitials
                )
            }

            ParcheButton(
                text = event.ctaText,
                onClick = { event.onJoinClick?.invoke() },
                enabled = ctaEnabled,
                style = ctaStyle,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun HeaderRow(
    title: String,
    sportType: EventSportType,
    statusText: String,
    statusType: ParcheStatusBadgeType
) {
    val spacing = ParcheThemeTokens.spacing

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
        verticalAlignment = Alignment.Top
    ) {
        SportIconContainer(sportType = sportType)

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = TextPrimary
            )

            ParcheStatusBadge(
                text = statusText,
                type = statusType
            )
        }
    }
}

@Composable
private fun SportIconContainer(
    sportType: EventSportType
) {
    val icon = sportIcon(sportType)

    Box(
        modifier = Modifier
            .size(44.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(ParcheGreenLight),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = sportType.name,
            tint = ParcheGreenDark
        )
    }
}

@Composable
private fun InfoRow(
    icon: ImageVector,
    text: String
) {
    val spacing = ParcheThemeTokens.spacing

    Row(
        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TextMuted,
            modifier = Modifier.size(16.dp)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = TextSecondary
        )
    }
}

@Composable
private fun EventMetaPill(
    icon: ImageVector,
    text: String
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(ParcheRadius.Medium))
            .background(Gray100)
            .padding(horizontal = 10.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TextMuted,
            modifier = Modifier.size(14.dp)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary
        )
    }
}

@Composable
private fun RatingAndSpots(
    ratingText: String,
    spotsText: String
) {
    val spacing = ParcheThemeTokens.spacing

    Column(
        verticalArrangement = Arrangement.spacedBy(spacing.sm)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color(0xFFF5B301),
                modifier = Modifier.size(16.dp)
            )

            Text(
                text = ratingText,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                color = TextPrimary
            )
        }

        Text(
            text = spotsText,
            style = MaterialTheme.typography.bodyMedium,
            color = TextMuted
        )
    }
}

@Composable
private fun ParticipantsPreview(
    initials: List<String>
) {
    if (initials.isEmpty()) return

    val maxVisible = 3
    val visible = initials.take(maxVisible)
    val remaining = (initials.size - maxVisible).coerceAtLeast(0)

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        visible.forEachIndexed { index, item ->
            Box(
                modifier = Modifier.offset(x = if (index == 0) 0.dp else (-8 * index).dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(ParcheWhite)
                        .padding(2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    ParcheAvatar(
                        initials = item,
                        size = ParcheAvatarSize.Small
                    )
                }
            }
        }

        if (remaining > 0) {
            Box(
                modifier = Modifier
                    .offset(x = (-8 * visible.size).dp)
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Gray200),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+$remaining",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextSecondary
                )
            }
        }
    }
}

private fun sportIcon(type: EventSportType): ImageVector {
    return when (type) {
        EventSportType.Futbol -> Icons.Filled.SportsSoccer
        EventSportType.Tenis -> Icons.Filled.SportsTennis
        EventSportType.Basket -> Icons.Filled.SportsBasketball
        EventSportType.Social -> Icons.Outlined.Place
    }
}

private fun sportLevelIcon(type: EventSportType): ImageVector {
    return when (type) {
        EventSportType.Futbol -> Icons.Filled.SportsSoccer
        EventSportType.Tenis -> Icons.Filled.SportsTennis
        EventSportType.Basket -> Icons.Filled.SportsBasketball
        EventSportType.Social -> Icons.Outlined.Place
    }
}