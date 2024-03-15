package com.example.ktorchatapp.domain.model

import java.util.Date

data class Message(
    val text: String,
    val formattedTime: String,
    val username: String
)
