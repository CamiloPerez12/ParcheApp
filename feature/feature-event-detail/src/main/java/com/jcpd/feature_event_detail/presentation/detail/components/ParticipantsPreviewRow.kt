package com.jcpd.feature_event_detail.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jcpd.core_ui.components.ParcheAvatar
import com.jcpd.core_ui.components.ParcheAvatarSize
import com.jcpd.core_ui.components.ParcheCard
import com.jcpd.core_ui.theme.Gray200
import com.jcpd.core_ui.theme.ParcheGreen
import com.jcpd.core_ui.theme.ParcheWhite
import com.jcpd.core_ui.theme.TextPrimary
import com.jcpd.core_ui.theme.TextSecondary
import com.jcpd.feature_event_detail.presentation.detail.ParticipantUi

@Composable
fun ParticipantsPreviewRow(
    title: String,
    participants: List<ParticipantUi>,
    joinedPlayers: Int,
    totalPlayers: Int,
    modifier: Modifier = Modifier
) {
    ParcheCard(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = TextPrimary
            )

            Text(
                text = "$joinedPlayers/$totalPlayers jugadores",
                style = MaterialTheme.typography.bodyLarge,
                color = TextSecondary
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                participants.take(5).forEach { participant ->
                    ParticipantRow(participant = participant)
                }
            }
        }
    }
}

@Composable
private fun ParticipantRow(
    participant: ParticipantUi
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ParcheAvatar(
            initials = participant.initials,
            size = ParcheAvatarSize.Medium
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = participant.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextPrimary
                )

                if (participant.isVerified) {
                    Icon(
                        imageVector = Icons.Outlined.Verified,
                        contentDescription = null,
                        tint = ParcheGreen
                    )
                }
            }

            Text(
                text = "⭐ ${participant.rating}",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )
        }

        if (participant.isOrganizer) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Gray200)
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Admin",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextPrimary
                )
            }
        }
    }
}