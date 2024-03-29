package com.example.ktorchatapp.presentation.chat

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorchatapp.data.remote.ChatSocketService
import com.example.ktorchatapp.data.remote.MessageService
import com.example.ktorchatapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageService: MessageService,
    private val chatSocketService: ChatSocketService,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _messageText = mutableStateOf("")
    val messageText: State<String> = _messageText

    private val _state = mutableStateOf(ChatState())
    val state: State<ChatState> = _state

    private val _toastEvents = MutableSharedFlow<String>()
    val toastEvents = _toastEvents.asSharedFlow()

    fun connectToChat() {
        getAllMessages()
        savedStateHandle.get<String>("username")?.let { username ->
            viewModelScope.launch {
                when (val result = chatSocketService.initSession(username)) {
                    is Resource.Success -> {
                        chatSocketService.observeMessages().onEach { message ->
                            val newList = _state.value.messageList.toMutableList().apply {
                                add(0, message)
                            }
                            _state.value = _state.value.copy(messageList = newList)
                        }.launchIn(viewModelScope)
                    }

                    is Resource.Error -> {
                        _toastEvents.emit(result.message ?: "Unknown Error")
                    }
                }
            }
        }
    }

    fun onMessageChange(message: String) {
        _messageText.value = message
    }

    fun disconnect() {
        viewModelScope.launch {
            chatSocketService.closeSession()
        }
    }

    fun sendMessage() {
        viewModelScope.launch {
            if (_messageText.value.isNotEmpty()) {
                chatSocketService.sendMessage(_messageText.value)
                _messageText.value = ""
            }
        }
    }

    private fun getAllMessages() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val results = messageService.getAllMessages()
            _state.value = _state.value.copy(messageList = results, isLoading = true)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disconnect()
    }
}