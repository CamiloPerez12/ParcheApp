package com.jcpd.feature_chat.presentation.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcpd.feature_chat.domain.usecase.GetMessagesUseCase
import com.jcpd.feature_chat.domain.usecase.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
            val eventInfo = resolveEventInfo(eventId)

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null,
                eventTitle = eventInfo.title,
                eventSubtitle = eventInfo.subtitle
            )

            runCatching {
                getMessages(eventId)
            }.onSuccess { msgs ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    messages = msgs,
                    errorMessage = null
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "No se pudieron cargar los mensajes"
                )
            }
        }
    }

    fun onMessageChange(text: String) {
        _uiState.value = _uiState.value.copy(inputText = text)
    }

    fun send() {
        val text = _uiState.value.inputText.trim()
        if (text.isBlank() || _uiState.value.isSending) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isSending = true,
                errorMessage = null
            )

            runCatching {
                sendMessage(eventId, text)
                getMessages(eventId)
            }.onSuccess { msgs ->
                _uiState.value = _uiState.value.copy(
                    isSending = false,
                    inputText = "",
                    messages = msgs,
                    errorMessage = null
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    isSending = false,
                    errorMessage = "No se pudo enviar el mensaje"
                )
            }
        }
    }

    private fun resolveEventInfo(eventId: String): ChatEventInfo {
        return when (eventId) {
            "event_1" -> ChatEventInfo(
                title = "Fútbol 5 en Galerías",
                subtitle = "Hoy • 7:00 PM • Cancha El Parque"
            )

            "event_2" -> ChatEventInfo(
                title = "Tenis en Chapinero",
                subtitle = "Mañana • 6:30 PM • Club Norte"
            )

            "event_3" -> ChatEventInfo(
                title = "Basket en Teusaquillo",
                subtitle = "Hoy • 8:00 PM • Parque Central"
            )

            "event_4" -> ChatEventInfo(
                title = "Plan social en Usaquén",
                subtitle = "Hoy • 9:30 PM • Parque 93"
            )

            else -> ChatEventInfo(
                title = "Evento",
                subtitle = "Detalles por confirmar"
            )
        }
    }
}

private data class ChatEventInfo(
    val title: String,
    val subtitle: String
)