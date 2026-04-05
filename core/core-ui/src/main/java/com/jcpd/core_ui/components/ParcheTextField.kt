package com.jcpd.core_ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jcpd.core_ui.theme.ErrorRed
import com.jcpd.core_ui.theme.Gray200
import com.jcpd.core_ui.theme.Gray300
import com.jcpd.core_ui.theme.ParcheGreen
import com.jcpd.core_ui.theme.ParcheRadius
import com.jcpd.core_ui.theme.ParcheWhite
import com.jcpd.core_ui.theme.TextMuted
import com.jcpd.core_ui.theme.TextPrimary

@Composable
fun ParcheTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    isError: Boolean = false,
    supportingText: String? = null,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = singleLine,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        placeholder = placeholder?.let {
            {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextMuted
                )
            }
        },
        isError = isError,
        supportingText = supportingText?.let {
            {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isError) ErrorRed else TextMuted
                )
            }
        },
        shape = RoundedCornerShape(ParcheRadius.Medium),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = ParcheWhite,
            unfocusedContainerColor = ParcheWhite,
            disabledContainerColor = Gray200,
            errorContainerColor = ParcheWhite,

            focusedBorderColor = ParcheGreen,
            unfocusedBorderColor = Gray300,
            disabledBorderColor = Gray200,
            errorBorderColor = ErrorRed,

            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary,
            cursorColor = ParcheGreen,

            focusedLabelColor = ParcheGreen,
            unfocusedLabelColor = TextMuted,
            errorLabelColor = ErrorRed
        )
    )
}