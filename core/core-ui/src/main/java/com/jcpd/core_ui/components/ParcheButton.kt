package com.jcpd.core_ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jcpd.core_ui.theme.Gray300
import com.jcpd.core_ui.theme.ParcheGreen
import com.jcpd.core_ui.theme.ParcheGreenDark
import com.jcpd.core_ui.theme.ParcheGreenLight
import com.jcpd.core_ui.theme.ParcheRadius
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.ParcheWhite
import com.jcpd.core_ui.theme.TextMuted

enum class ParcheButtonStyle {
    Primary,
    Secondary,
    Outline,
    Ghost
}

@Composable
fun ParcheButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ParcheButtonStyle = ParcheButtonStyle.Primary,
    content: (@Composable RowScope.() -> Unit)? = null
) {
    val elevation = ParcheThemeTokens.elevation
    val buttonModifier = modifier.height(48.dp)

    when (style) {
        ParcheButtonStyle.Primary -> {
            Button(
                onClick = onClick,
                enabled = enabled,
                modifier = buttonModifier,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(ParcheRadius.Large),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = elevation.fab,
                    pressedElevation = elevation.pressed,
                    disabledElevation = 0.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ParcheGreen,
                    contentColor = ParcheWhite,
                    disabledContainerColor = Gray300,
                    disabledContentColor = TextMuted
                )
            ) {
                if (content != null) {
                    content()
                } else {
                    Text(
                        text = text,
                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }
        }

        ParcheButtonStyle.Secondary -> {
            Button(
                onClick = onClick,
                enabled = enabled,
                modifier = buttonModifier,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(ParcheRadius.Large),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ParcheGreenLight,
                    contentColor = ParcheGreenDark,
                    disabledContainerColor = Gray300,
                    disabledContentColor = TextMuted
                )
            ) {
                if (content != null) {
                    content()
                } else {
                    Text(
                        text = text,
                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        ParcheButtonStyle.Outline -> {
            OutlinedButton(
                onClick = onClick,
                enabled = enabled,
                modifier = buttonModifier,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(ParcheRadius.Large),
                border = androidx.compose.foundation.BorderStroke(1.dp, Gray300),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = ParcheGreen,
                    disabledContentColor = TextMuted
                )
            ) {
                if (content != null) {
                    content()
                } else {
                    Text(
                        text = text,
                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        ParcheButtonStyle.Ghost -> {
            TextButton(
                onClick = onClick,
                enabled = enabled,
                modifier = buttonModifier,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(ParcheRadius.Large),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = ParcheGreen,
                    disabledContentColor = TextMuted
                )
            ) {
                if (content != null) {
                    content()
                } else {
                    Text(
                        text = text,
                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}