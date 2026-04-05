package com.jcpd.feature_event_detail.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jcpd.core_ui.components.ParcheButton
import com.jcpd.core_ui.components.ParcheButtonStyle
import com.jcpd.core_ui.theme.ParcheBackground
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.feature_event_detail.R
import com.jcpd.feature_event_detail.presentation.detail.components.EventDetailHeader
import com.jcpd.feature_event_detail.presentation.detail.components.EventInfoCard
import com.jcpd.feature_event_detail.presentation.detail.components.JoinActionSection
import com.jcpd.feature_event_detail.presentation.detail.components.OrganizerCard
import com.jcpd.feature_event_detail.presentation.detail.components.ParticipantsPreviewRow

@Composable
fun EventDetailScreen(
    eventId: String,
    onBack: () -> Unit,
    onOpenChat: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EventDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    EventDetailContent(
        uiState = uiState,
        modifier = modifier,
        onBack = onBack,
        onRetry = viewModel::loadEventDetail,
        onOpenChat = onOpenChat
    )
}

@Composable
private fun EventDetailContent(
    uiState: EventDetailUiState,
    onBack: () -> Unit,
    onRetry: () -> Unit,
    onOpenChat: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = ParcheThemeTokens.spacing

    when {
        uiState.isLoading -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(ParcheBackground),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        uiState.errorMessageRes != null -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(ParcheBackground)
                    .padding(spacing.xl),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.lg),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(uiState.errorMessageRes),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    ParcheButton(
                        text = stringResource(R.string.event_detail_retry),
                        onClick = onRetry,
                        style = ParcheButtonStyle.Primary
                    )
                }
            }
        }

        uiState.eventDetail != null -> {
            val event = uiState.eventDetail

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(ParcheBackground)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(spacing.lg),
                    contentPadding = PaddingValues(
                        start = spacing.lg,
                        end = spacing.lg,
                        top = spacing.lg,
                        bottom = spacing.huge + spacing.xxl
                    )
                ) {
                    item {
                        EventDetailHeader(
                            title = event.title,
                            statusText = event.statusText,
                            statusType = event.statusType,
                            onBack = onBack
                        )
                    }

                    item {
                        EventInfoCard(
                            event = event,
                            detailsSectionTitle = uiState.detailsSectionTitleRes
                                ?.let { stringResource(it) }
                                .orEmpty(),
                            locationSectionTitle = uiState.locationSectionTitleRes
                                ?.let { stringResource(it) }
                                .orEmpty()
                        )
                    }

                    item {
                        OrganizerCard(
                            title = uiState.organizerSectionTitleRes
                                ?.let { stringResource(it) }
                                .orEmpty(),
                            organizer = event.organizer
                        )
                    }

                    item {
                        ParticipantsPreviewRow(
                            title = uiState.participantsSectionTitleRes
                                ?.let { stringResource(it) }
                                .orEmpty(),
                            participants = event.participants,
                            joinedPlayers = event.joinedPlayers,
                            totalPlayers = event.totalPlayers
                        )
                    }
                }

                JoinActionSection(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .navigationBarsPadding()
                        .padding(
                            start = spacing.lg,
                            end = spacing.lg,
                            bottom = spacing.lg
                        ),
                    joinButtonText = uiState.joinButtonTextRes
                        ?.let { stringResource(it) }
                        .orEmpty(),
                    openChatButtonText = uiState.openChatButtonTextRes
                        ?.let { stringResource(it) }
                        .orEmpty(),
                    state = event.state,
                    onJoinClick = onOpenChat,
                    onOpenChatClick = onOpenChat
                )
            }
        }
    }
}