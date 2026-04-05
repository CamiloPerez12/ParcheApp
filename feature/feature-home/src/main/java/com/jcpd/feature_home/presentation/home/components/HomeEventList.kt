package com.jcpd.feature_home.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jcpd.core_ui.components.EventCard
import com.jcpd.core_ui.components.EventCardUiModel
import com.jcpd.core_ui.theme.ParcheThemeTokens

@Composable
fun HomeEventList(
    events: List<EventCardUiModel>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues()
) {
    val spacing = ParcheThemeTokens.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(contentPadding),
        verticalArrangement = Arrangement.spacedBy(spacing.lg)
    ) {
        events.forEach { event ->
            EventCard(event = event)
        }
    }
}