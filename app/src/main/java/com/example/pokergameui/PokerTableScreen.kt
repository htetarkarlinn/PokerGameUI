package com.example.pokergameui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokerTableScreen() {
    val communityCardImages = listOf(
        R.drawable.ten_of_clubs,
        R.drawable.three_of_hearts,
        R.drawable.two_of_diamonds,
        R.drawable.queen_of_hearts,
        R.drawable.ace_of_hearts
    )

    val playerCardImages = listOf(
        R.drawable.six_of_spades,
        R.drawable.seven_of_clubs
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Poker Table", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF00796B)
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Background Image
            Image(
                painter = painterResource(id = R.drawable.table_background),
                contentDescription = "Table Background",
                modifier = Modifier.fillMaxSize()
            )

            // Poker Table Content
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CommunityCardsSection(cardImages = communityCardImages)

                Spacer(modifier = Modifier.height(24.dp))

                GameStatusSection(potAmount = 1000, currentPlayer = "Player 1")

                Spacer(modifier = Modifier.weight(1f))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PlayerCardsSection(playerCardImages = playerCardImages)
                    Spacer(modifier = Modifier.height(24.dp))
                    PlayerControls(
                        onBetClick = { /* Add Bet Logic */ },
                        onCheckClick = { /* Add Check Logic */ },
                        onFoldClick = { /* Add Fold Logic */ }
                    )
                }
            }
        }
    }
}


