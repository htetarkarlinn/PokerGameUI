package com.example.pokergameui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "lobby"
    ) {
        composable("lobby") {
            LobbyScreen(navController)
        }
        composable("poker_table") {
            PokerTableScreen()
        }
}