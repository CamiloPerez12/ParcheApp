package com.jcpd.feature_chat.presentation.chat.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jcpd.core_ui.theme.Gray100
import com.jcpd.core_ui.theme.Gray200
import com.jcpd.core_ui.theme.Gray300
import com.jcpd.core_ui.theme.ParcheGreen
import com.jcpd.core_ui.theme.ParcheRadius
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.ParcheWhite
import com.jcpd.core_ui.theme.TextMuted
import com.jcpd.core_ui.theme.TextPrimary

@Composable
fun ChatInputBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSendClick: () -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isSending: Boolean = false
) {
    val spacing = ParcheThemeTokens.spacing
    val isSendEnabled = value.isNotBlank() && !isSending

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Gray200,
                shape = RoundedCornerShape(ParcheRadius.Large)
            ),
        shape = RoundedCornerShape(ParcheRadius.Large),
        color = ParcheWhite,
        tonalElevation = 3.dp,
        shadowElevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = spacing.sm,
                    vertical = spacing.sm
                ),
            horizontalArrangement = Arrangement.spacedBy(spacing.sm),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 48.dp),
                textStyle = MaterialTheme.typography.bodyLarge,
                placeholder = {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextMuted
                    )
                },
                shape = RoundedCornerShape(ParcheRadius.Medium),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Gray100,
                    unfocusedContainerColor = Gray100,
                    disabledContainerColor = Gray100,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = ParcheGreen,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                ),
                maxLines = 4,
                enabled = !isSending
            )

            IconButton(
                onClick = onSendClick,
                enabled = isSendEnabled
            ) {
                Icon(
                    imageVector = Icons.Outlined.Send,
                    contentDescription = null,
                    tint = if (isSendEnabled) ParcheGreen else Gray300
                )
            }
        }
    }
}