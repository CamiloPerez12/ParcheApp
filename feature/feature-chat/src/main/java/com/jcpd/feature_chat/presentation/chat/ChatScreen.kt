package com.jcpd.feature_chat.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jcpd.core_ui.components.ParcheButton
import com.jcpd.core_ui.components.ParcheButtonStyle
import com.jcpd.core_ui.theme.ParcheChatBackground
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.TextMuted
import com.jcpd.feature_chat.R
import com.jcpd.feature_chat.presentation.chat.components.ChatEventBanner
import com.jcpd.feature_chat.presentation.chat.components.ChatHeader
import com.jcpd.feature_chat.presentation.chat.components.ChatInputBar
import com.jcpd.feature_chat.presentation.chat.components.MessageBubble

@Composable
fun ChatScreen(
    eventId: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val spacing = ParcheThemeTokens.spacing

    LaunchedEffect(uiState.messages.size, uiState.hasAccess) {
        if (uiState.hasAccess && uiState.messages.isNotEmpty()) {
            listState.animateScrollToItem(uiState.messages.lastIndex)
        }
    }

    when {
        uiState.isLoading -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(ParcheChatBackground),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        !uiState.hasAccess -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(ParcheChatBackground)
            ) {
                ChatHeader(
                    title = stringResource(R.string.chat_title),
                    onBack = onBack
                )

                NoChatAccessContent(
                    eventTitle = uiState.eventTitle,
                    eventSubtitle = uiState.eventSubtitle,
                    onBackToEvent = onBack,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        else -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(ParcheChatBackground)
                    .imePadding()
            ) {
                ChatHeader(
                    title = stringResource(R.string.chat_title),
                    onBack = onBack
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(horizontal = spacing.lg)
                ) {
                    ChatEventBanner(
                        title = uiState.eventTitle,
                        subtitle = uiState.eventSubtitle,
                        modifier = Modifier.padding(top = spacing.lg)
                    )

                    if (uiState.errorMessage != null) {
                        Text(
                            text = uiState.errorMessage.orEmpty(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextMuted,
                            modifier = Modifier.padding(top = spacing.md)
                        )
                    }

                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .padding(top = spacing.lg),
                        contentPadding = PaddingValues(bottom = spacing.md),
                        verticalArrangement = Arrangement.spacedBy(spacing.md)
                    ) {
                        items(
                            items = uiState.messages,
                            key = { it.id }
                        ) { message ->
                            MessageBubble(message = message)
                        }
                    }
                }

                ChatInputBar(
                    value = uiState.inputText,
                    onValueChange = viewModel::onMessageChange,
                    onSendClick = viewModel::send,
                    placeholder = stringResource(R.string.chat_input_placeholder),
                    isSending = uiState.isSending,
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(
                            start = spacing.lg,
                            end = spacing.lg,
                            bottom = spacing.lg
                        )
                )
            }
        }
    }
}

@Composable
private fun NoChatAccessContent(
    eventTitle: String,
    eventSubtitle: String,
    onBackToEvent: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = ParcheThemeTokens.spacing

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = spacing.xl),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing.md)
        ) {
            Text(
                text = "Join this event to access the chat",
                style = MaterialTheme.typography.headlineSmall
            )

            if (eventTitle.isNotBlank()) {
                Text(
                    text = eventTitle,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            if (eventSubtitle.isNotBlank()) {
                Text(
                    text = eventSubtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextMuted
                )
            }

            ParcheButton(
                text = "Back to event",
                onClick = onBackToEvent,
                style = ParcheButtonStyle.Primary
            )
        }
    }
}