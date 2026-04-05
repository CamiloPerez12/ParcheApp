package com.jcpd.feature_event_detail.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jcpd.core_ui.components.ParcheAvatar
import com.jcpd.core_ui.components.ParcheAvatarSize
import com.jcpd.core_ui.components.ParcheCard
import com.jcpd.core_ui.theme.ParcheGreen
import com.jcpd.core_ui.theme.TextMuted
import com.jcpd.core_ui.theme.TextPrimary
import com.jcpd.core_ui.theme.TextSecondary
import com.jcpd.feature_event_detail.presentation.detail.OrganizerUi
import java.text.DecimalFormat
import androidx.compose.material.icons.outlined.Verified

@Composable
fun OrganizerCard(
    title: String,
    organizer: OrganizerUi,
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ParcheAvatar(
                    initials = organizer.name.take(2).uppercase(),
                    size = ParcheAvatarSize.Large
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = organizer.name,
                            style = MaterialTheme.typography.headlineSmall,
                            color = TextPrimary
                        )

                        if (organizer.isVerified) {
                            Icon(
                                imageVector = Icons.Outlined.Verified,
                                contentDescription = null,
                                tint = ParcheGreen
                            )
                        }
                    }

                    Text(
                        text = organizer.username,
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextSecondary
                    )

                    Text(
                        text = organizer.city,
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextMuted
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = null,
                                tint = androidx.compose.ui.graphics.Color(0xFFF5B301)
                            )
                            Text(
                                text = DecimalFormat("#.#").format(organizer.rating),
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextPrimary
                            )
                        }

                        Text(
                            text = "${organizer.attendancePercentage}% asistencia",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary
                        )
                    }
                }
            }
        }
    }
}