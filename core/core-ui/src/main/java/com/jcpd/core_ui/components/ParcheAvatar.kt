package com.jcpd.core_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jcpd.core_ui.theme.Gray200
import com.jcpd.core_ui.theme.ParcheGreenDark

enum class ParcheAvatarSize(val sizeDp: androidx.compose.ui.unit.Dp) {
    Small(32.dp),
    Medium(40.dp),
    Large(64.dp),
    ExtraLarge(96.dp)
}

@Composable
fun ParcheAvatar(
    initials: String,
    modifier: Modifier = Modifier,
    size: ParcheAvatarSize = ParcheAvatarSize.Medium
) {
    Box(
        modifier = modifier
            .size(size.sizeDp)
            .background(Gray200, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            style = when (size) {
                ParcheAvatarSize.Small -> MaterialTheme.typography.labelMedium
                ParcheAvatarSize.Medium -> MaterialTheme.typography.labelMedium
                ParcheAvatarSize.Large -> MaterialTheme.typography.headlineSmall
                ParcheAvatarSize.ExtraLarge -> MaterialTheme.typography.headlineMedium
            },
            color = ParcheGreenDark
        )
    }
}