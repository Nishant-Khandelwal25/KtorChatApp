package com.example.ktorchatapp.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ktorchatapp.presentation.chat.ChatViewModel

@Composable
fun SendMessageItem(viewModel: ChatViewModel) {
    Row(Modifier.fillMaxWidth()) {
        TextField(
            value = viewModel.messageText.value,
            onValueChange = { viewModel.onMessageChange(it) },
            placeholder = { Text(text = "Enter message") },
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = {
            viewModel.sendMessage()

        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Send Message Icon"
            )
        }
    }
}