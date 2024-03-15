package com.example.ktorchatapp.data.remote

import com.example.ktorchatapp.domain.model.Message
import com.example.ktorchatapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {
    suspend fun initSession(
        username: String
    ): Resource<Unit>

    suspend fun sendMessage(message: String)

    fun observeMessages(): Flow<Message>

    suspend fun closeSession()

    companion object {
        const val HTTP_BASE_URL = "ws://192.168.1.5:8080"
    }

    sealed class EndPoints(val url: String) {
        data object ChatSocket : EndPoints("$HTTP_BASE_URL/chat-socket")
    }
}