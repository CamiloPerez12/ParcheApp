package com.jcpd.core_ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jcpd.core_ui.theme.ParcheRadius
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.ParcheWhite

@Composable
fun ParcheCard(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(ParcheThemeTokens.spacing.lg),
    content: @Composable () -> Unit
) {
    val elevation = ParcheThemeTokens.elevation

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(ParcheRadius.Large),
        colors = CardDefaults.cardColors(containerColor = ParcheWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation.card)
    ) {
        Column(modifier = Modifier.padding(contentPadding)) {
            content()
        }
    }
}