package com.example.ktorchatapp.navigation

sealed class Screens( val screenName: String) {
    data object UsernameScreen : Screens("username_screen")
    data object ChatScreen : Screens("chat_screen/{username}") {
        fun passUsername(username: String): String {
            return "chat_screen/$username"
        }
    }
}