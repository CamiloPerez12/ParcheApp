package com.jcpd.feature_chat.presentation.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcpd.feature_chat.domain.usecase.GetMessagesUseCase
import com.jcpd.feature_chat.domain.usecase.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getMessages: GetMessagesUseCase,
    private val sendMessage: SendMessageUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val eventId: String = savedStateHandle["eventId"] ?: ""

    private val _uiState = MutableStateFlow(ChatUiState(isLoading = true))
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        loadMessages()
    }

    private fun loadMessages() {
        viewModelScope.launch {
            val msgs = getMessages(eventId)
            _uiState.value = ChatUiState(
                isLoading = false,
                messages = msgs
            )
        }
    }

    fun onMessageChange(text: String) {
        _uiState.value = _uiState.value.copy(inputText = text)
    }

    fun send() {
        val text = _uiState.value.inputText
        if (text.isBlank()) return

        viewModelScope.launch {
            sendMessage(eventId, text)
            loadMessages()
            _uiState.value = _uiState.value.copy(inputText = "")
        }
    }
}