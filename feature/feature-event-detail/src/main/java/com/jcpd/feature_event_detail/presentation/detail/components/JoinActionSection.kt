package com.jcpd.feature_event_detail.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jcpd.core_ui.components.EventCardState
import com.jcpd.core_ui.components.ParcheButton
import com.jcpd.core_ui.components.ParcheButtonStyle
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.ParcheWhite

@Composable
fun JoinActionSection(
    joinButtonText: String,
    openChatButtonText: String,
    state: EventCardState,
    onJoinClick: () -> Unit,
    onOpenChatClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = ParcheThemeTokens.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(ParcheWhite)
            .padding(spacing.lg),
        verticalArrangement = Arrangement.spacedBy(spacing.md)
    ) {
        when (state) {

            EventCardState.Default -> {
                ParcheButton(
                    text = joinButtonText,
                    onClick = onJoinClick,
                    style = ParcheButtonStyle.Primary,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            EventCardState.Joined -> {
                ParcheButton(
                    text = openChatButtonText,
                    onClick = onOpenChatClick,
                    style = ParcheButtonStyle.Primary,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            EventCardState.Full -> {
                ParcheButton(
                    text = joinButtonText,
                    onClick = {},
                    enabled = false,
                    style = ParcheButtonStyle.Secondary,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}