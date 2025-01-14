package com.example.pokergameui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


data class PokerTable(
    val tableName: String,
    val currentPlayer: Int,
    val maxPlayer: Int,
    val minBet: Int
)

class LobbyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokerGameApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokerGameApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Poker Game", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF00796B)
                )
            )
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "lobby",
            modifier = Modifier.padding(padding)
        ) {
            addLobbyScreen(navController)
            addCreateTableScreen(navController)
            addJoinTableScreen(navController)
            addScoreboardScreen(navController)
        }
    }
}

fun NavGraphBuilder.addLobbyScreen(navController: NavController) {
    composable("lobby") {
        LobbyScreen(
            navController = navController,
            onCreateTableClicked = { navController.navigate("create_table") },
            onJoinTableClicked = { navController.navigate("join_table") },
            onClickGameRule = {
                val youtubeUrl = "https://www.youtube.com/watch?v=JOomXP-r1wY"
                navController.context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(youtubeUrl)
                    )
                )
            }
        )
    }
}

fun NavGraphBuilder.addCreateTableScreen(navController: NavController) {
    composable("create_table") {
        CreateTableScreen(
            onCreateTable = { tableName, numberOfPlayers, minBet ->
                Log.d(
                    "CreateTable",
                    "Table: $tableName, Players: $numberOfPlayers, Min Bet: $minBet"
                )
                navController.navigate("Lobby")
            }
        )
    }
}

fun NavGraphBuilder.addJoinTableScreen(navController: NavController) {
    composable("join_table") {
        JoinTableScreen(navController)
    }
}

fun NavGraphBuilder.addScoreboardScreen(navController: NavController) {
    composable("scoreboard") {
        ScoreboardScreen(navController)
    }
}

