package com.example.ktorchatapp.presentation.chat

import com.example.ktorchatapp.domain.model.Message

data class ChatState(
    val messageList: List<Message> = emptyList(),
    val isLoading: Boolean = false
)