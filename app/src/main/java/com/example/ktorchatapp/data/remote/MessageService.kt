package com.example.ktorchatapp.data.remote

import com.example.ktorchatapp.domain.model.Message

interface MessageService {
    suspend fun getAllMessages(): List<Message>

    companion object{
        const val BASE_URL = "http://192.168.1.5:8080"
    }

    sealed class EndPoints(val url: String){
        data object GetAllMessages: EndPoints("$BASE_URL/messages")
    }
}