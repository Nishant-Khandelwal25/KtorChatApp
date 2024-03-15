package com.example.ktorchatapp.data.remote

import com.example.ktorchatapp.data.remote.dto.MessageDto
import com.example.ktorchatapp.domain.model.Message
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.get

class MessageServiceImpl(
    private val client: HttpClient
) : MessageService {
    override suspend fun getAllMessages(): List<Message> {
        return try {
            client.get(MessageService.EndPoints.GetAllMessages.url).body<List<MessageDto>>().map {
                it.toMessage()
            }
        } catch (ex: Exception) {
            emptyList()
        }
    }
}