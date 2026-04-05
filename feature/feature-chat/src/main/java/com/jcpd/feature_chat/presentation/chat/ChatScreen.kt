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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jcpd.core_ui.theme.ParcheChatBackground
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.feature_chat.R
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

    LaunchedEffect(uiState.messages.size) {
        if (uiState.messages.isNotEmpty()) {
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

        else -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(ParcheChatBackground)
                    .imePadding()
            ) {
                ChatHeader(
                    title = stringResource(R.string.chat_title),
                    subtitle = stringResource(R.string.chat_subtitle),
                    onBack = onBack
                )

                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    contentPadding = PaddingValues(
                        horizontal = spacing.lg,
                        vertical = spacing.lg
                    ),
                    verticalArrangement = Arrangement.spacedBy(spacing.md)
                ) {
                    items(
                        items = uiState.messages,
                        key = { it.id }
                    ) { message ->
                        MessageBubble(message = message)
                    }
                }

                ChatInputBar(
                    value = uiState.inputText,
                    onValueChange = viewModel::onMessageChange,
                    onSendClick = viewModel::send,
                    placeholder = stringResource(R.string.chat_input_placeholder),
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