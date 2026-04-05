package com.jcpd.core_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jcpd.core_ui.theme.ActiveBadgeBg
import com.jcpd.core_ui.theme.ActiveBadgeText
import com.jcpd.core_ui.theme.FullBadgeBg
import com.jcpd.core_ui.theme.FullBadgeText
import com.jcpd.core_ui.theme.LaterBadgeBg
import com.jcpd.core_ui.theme.LaterBadgeText
import com.jcpd.core_ui.theme.ParcheRadius
import com.jcpd.core_ui.theme.SoonBadgeBg
import com.jcpd.core_ui.theme.SoonBadgeText

enum class ParcheStatusBadgeType {
    Active,
    Soon,
    Later,
    Full
}

@Composable
fun ParcheStatusBadge(
    text: String,
    type: ParcheStatusBadgeType,
    modifier: Modifier = Modifier
) {
    val (background, content) = when (type) {
        ParcheStatusBadgeType.Active -> ActiveBadgeBg to ActiveBadgeText
        ParcheStatusBadgeType.Soon -> SoonBadgeBg to SoonBadgeText
        ParcheStatusBadgeType.Later -> LaterBadgeBg to LaterBadgeText
        ParcheStatusBadgeType.Full -> FullBadgeBg to FullBadgeText
    }

    Row(
        modifier = modifier
            .background(
                color = background,
                shape = RoundedCornerShape(ParcheRadius.Medium)
            )
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = content
        )
    }
}