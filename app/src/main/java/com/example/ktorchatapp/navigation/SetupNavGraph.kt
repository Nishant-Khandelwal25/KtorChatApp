package com.example.ktorchatapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ktorchatapp.presentation.chat.ChatScreen
import com.example.ktorchatapp.presentation.username.UsernameScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.UsernameScreen.screenName) {
        composable(route = Screens.UsernameScreen.screenName) {
            UsernameScreen(navController = navController)
        }

        composable(
            route = Screens.ChatScreen.screenName,
            arguments = listOf(navArgument("username") {
                type = NavType.StringType
                nullable = true
            })
        ) {
            ChatScreen(
                username = it.arguments?.getString("username")
            )
        }

    }
}