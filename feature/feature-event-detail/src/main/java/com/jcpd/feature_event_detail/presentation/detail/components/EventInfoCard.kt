package com.jcpd.feature_event_detail.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsBasketball
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jcpd.core_ui.components.EventSportType
import com.jcpd.core_ui.components.ParcheCard
import com.jcpd.core_ui.theme.TextMuted
import com.jcpd.core_ui.theme.TextPrimary
import com.jcpd.core_ui.theme.TextSecondary
import com.jcpd.feature_event_detail.presentation.detail.EventDetailUi
import java.text.DecimalFormat

@Composable
fun EventInfoCard(
    event: EventDetailUi,
    detailsSectionTitle: String,
    locationSectionTitle: String,
    modifier: Modifier = Modifier
) {
    ParcheCard(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = sportIcon(event.sportType),
                    contentDescription = null
                )

                Text(
                    text = event.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = TextPrimary
                )
            }

            Text(
                text = event.description,
                style = MaterialTheme.typography.bodyLarge,
                color = TextSecondary
            )

            Text(
                text = detailsSectionTitle,
                style = MaterialTheme.typography.titleMedium,
                color = TextPrimary
            )

            InfoLine(
                icon = Icons.Outlined.CalendarToday,
                text = event.dateLabel
            )

            InfoLine(
                icon = Icons.Outlined.Schedule,
                text = event.timeLabel
            )

            InfoLine(
                icon = Icons.Outlined.Groups,
                text = "${event.joinedPlayers}/${event.totalPlayers} jugadores • ${event.remainingSpots} cupos"
            )

            InfoLine(
                icon = Icons.Filled.Star,
                text = DecimalFormat("#.#").format(event.rating)
            )

            InfoLine(
                icon = Icons.Outlined.Place,
                text = event.levelLabel
            )

            if (!event.priceLabel.isNullOrBlank()) {
                InfoLine(
                    icon = Icons.Outlined.AttachMoney,
                    text = event.priceLabel
                )
            }

            Text(
                text = locationSectionTitle,
                style = MaterialTheme.typography.titleMedium,
                color = TextPrimary,
                modifier = Modifier.padding(top = 4.dp)
            )

            InfoLine(
                icon = Icons.Outlined.LocationOn,
                text = event.locationName
            )

            InfoLine(
                icon = Icons.Outlined.Place,
                text = event.address
            )

            InfoLine(
                icon = Icons.Outlined.LocationOn,
                text = "${DecimalFormat("#.#").format(event.distanceKm)} km"
            )
        }
    }
}

@Composable
private fun InfoLine(
    icon: ImageVector,
    text: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TextMuted
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = TextSecondary
        )
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