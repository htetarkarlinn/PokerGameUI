package com.example.pokergameui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommunityCardsSection(cardImages: List<Int>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        cardImages.forEach { cardImage ->
            Card(
                modifier = Modifier
                    .size(80.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Image(
                    painter = painterResource(id = cardImage),
                    contentDescription = "Community Card",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun GameStatusSection(potAmount: Int, currentPlayer: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pot: $potAmount Chips",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Current Turn: $currentPlayer",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
    }
}

@Composable
fun PlayerCardsSection(playerCardImages: List<Int>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        playerCardImages.forEach { cardImage ->
            Card(
                modifier = Modifier
                    .size(80.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Image(
                    painter = painterResource(id = cardImage),
                    contentDescription = "Player Card",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun PlayerControls(onBetClick: () -> Unit, onCheckClick: () -> Unit, onFoldClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = onBetClick, colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF00796B), contentColor = Color.White)) { Text("Bet") }
        Button(onClick = onCheckClick, colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF00796B), contentColor = Color.White)) { Text("Check") }
        Button(onClick = onFoldClick, colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF00796B), contentColor = Color.White)) { Text("Fold") }
    }
}