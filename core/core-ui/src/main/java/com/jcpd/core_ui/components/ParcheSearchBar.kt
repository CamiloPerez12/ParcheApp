package com.jcpd.core_ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jcpd.core_ui.theme.Gray200
import com.jcpd.core_ui.theme.ParcheRadius
import com.jcpd.core_ui.theme.ParcheWhite
import com.jcpd.core_ui.theme.TextMuted
import com.jcpd.core_ui.theme.TextSecondary

@Composable
fun ParcheSearchBar(
    placeholder: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onFilterClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = ParcheWhite,
                shape = RoundedCornerShape(ParcheRadius.Large)
            )
            .border(
                width = 1.dp,
                color = Gray200,
                shape = RoundedCornerShape(ParcheRadius.Large)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
                tint = TextMuted
            )
            androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(start = 10.dp))
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyLarge,
                color = TextSecondary
            )
        }

        if (onFilterClick != null) {
            Icon(
                imageVector = Icons.Outlined.Tune,
                contentDescription = "Filtros",
                tint = TextMuted,
                modifier = Modifier.clickable { onFilterClick() }
            )
        }
    }
}