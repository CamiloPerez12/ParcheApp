package com.jcpd.core_ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.jcpd.core_ui.theme.ParcheGreen
import com.jcpd.core_ui.theme.ParcheWhite
import com.jcpd.core_ui.theme.TextMuted

data class ParcheBottomNavItem(
    val label: String,
    val icon: ImageVector,
    val selected: Boolean
)

@Composable
fun ParcheBottomBar(
    items: List<ParcheBottomNavItem>,
    modifier: Modifier = Modifier,
    onItemClick: (index: Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(ParcheWhite)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, item ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onItemClick(index) }
                    .padding(vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = if (item.selected) ParcheGreen else TextMuted
                    )
                    androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(start = 6.dp))
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.labelMedium,
                        color = if (item.selected) ParcheGreen else TextMuted
                    )
                }
            }
        }
    }
}