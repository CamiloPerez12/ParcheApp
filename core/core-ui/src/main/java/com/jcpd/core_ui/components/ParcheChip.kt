package com.jcpd.core_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.jcpd.core_ui.theme.Gray300
import com.jcpd.core_ui.theme.ParcheGreen
import com.jcpd.core_ui.theme.ParcheGreenDark
import com.jcpd.core_ui.theme.ParcheRadius
import com.jcpd.core_ui.theme.ParcheWhite
import com.jcpd.core_ui.theme.TextSecondary

@Composable
fun ParcheChip(
    text: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    icon: ImageVector? = null,
    onClick: (() -> Unit)? = null
) {
    val shape = RoundedCornerShape(ParcheRadius.Medium)

    val backgroundColor = if (selected) ParcheGreen else ParcheWhite
    val contentColor = if (selected) ParcheWhite else TextSecondary
    val borderColor = if (selected) ParcheGreen else Gray300

    Row(
        modifier = modifier
            .defaultMinSize(minHeight = 36.dp)
            .then(
                if (onClick != null) {
                    Modifier.clickable { onClick() }
                } else {
                    Modifier
                }
            )
            .background(backgroundColor, shape)
            .border(1.dp, borderColor, shape)
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor
            )
            androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(start = 6.dp))
        }

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = if (selected) ParcheWhite else ParcheGreenDark
        )
    }
}