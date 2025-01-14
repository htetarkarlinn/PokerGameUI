package com.example.pokergameui.view

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokergameui.R

class InGameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the number of players from intent
        val numberOfPlayers = intent.getIntExtra("NUMBER_OF_PLAYERS", 8) // Default to 8 if not provided

        setContent {
            InGameScreen(this, numberOfPlayers)
        }
    }
}

@Composable
fun InGameScreen(activity: Activity, numberOfPlayers: Int) {
    // State for community cards
    val communityCards = remember { mutableStateListOf<Int>() }
    val deck = remember { (R.drawable.ace_of_clubs..R.drawable.two_of_spades).shuffled().toMutableList() }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background image of poker table
        Image(
            painter = painterResource(id = R.drawable.img_background), // Replace with your poker table drawable
            contentDescription = "Poker Table",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        CloseImageExample(activity = activity)

        // Dynamically positioned players
        PlayerPositions(numberOfPlayers)

        // Card holders in the center
        CardHolders(communityCards)

        // Buttons for Flop, Turn, River
        GameControlButtons(
            communityCards = communityCards,
            deck = deck
        )
    }
}

@Composable
fun CloseImageExample(activity: Activity) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_close), // Replace with your image resource
            contentDescription = "Close",
            modifier = Modifier
                .size(70.dp)
                .clickable { activity.finish() }
                .padding(16.dp)
        )
    }
}

@Composable
fun PlayerPositions(numberOfPlayers: Int) {
    val playerData = (1..numberOfPlayers).map { playerIndex ->
        PlayerData(
            playerName = "P$playerIndex",
            betAmount = (10 * playerIndex).toString(),
            profileImageRes = R.drawable.p1 + (playerIndex % 6) // Cycle through 6 images
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        playerData.forEachIndexed { index, player ->
            val position = getPlayerPosition(index, numberOfPlayers)
            PlayerBox(
                playerName = player.playerName,
                betAmount = player.betAmount,
                profileImageRes = player.profileImageRes,
                modifier = Modifier.align(position)
            )
        }
    }
}

@Composable
fun PlayerBox(playerName: String, betAmount: String, profileImageRes: Int, modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            shape = CircleShape,
            modifier = Modifier.size(50.dp),
            color = Color.Gray.copy(alpha = 0.8f)
        ) {
            Image(
                painter = painterResource(id = profileImageRes),
                contentDescription = "$playerName Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = playerName,
            fontSize = 14.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = betAmount,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Yellow,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun CardHolders(communityCards: List<Int>) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .size(650.dp, 130.dp)
                .background(color = Color.Transparent)
                .border(5.dp, Color.Black, RoundedCornerShape(100.dp))
                .wrapContentWidth()
        ) {
            communityCards.forEach { card ->
                Surface(
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .size(50.dp, 70.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(2.dp, Color.Gray),
                    color = Color.Transparent
                ) {
                    Image(
                        painter = painterResource(id = card),
                        contentDescription = "Community Card",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            repeat(5 - communityCards.size) {
                Surface(
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .size(50.dp, 70.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(2.dp, Color.Gray),
                    color = Color.Transparent
                ) {
                }
            }
        }
    }
}

@Composable
fun GameControlButtons(communityCards: MutableList<Int>, deck: MutableList<Int>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ControlButton("Flop") { flop(communityCards, deck) }
            ControlButton("Turn") { turn(communityCards, deck) }
            ControlButton("River") { river(communityCards, deck) }
        }
    }
}

fun flop(communityCards: MutableList<Int>, deck: MutableList<Int>) {
    if (communityCards.size < 3 && deck.size >= 3) {
        communityCards.addAll(deck.take(3))
        deck.removeAll(deck.take(3))
    }
}

fun turn(communityCards: MutableList<Int>, deck: MutableList<Int>) {
    if (communityCards.size == 3 && deck.isNotEmpty()) {
        communityCards.add(deck.removeAt(0))
    }
}

fun river(communityCards: MutableList<Int>, deck: MutableList<Int>) {
    if (communityCards.size == 4 && deck.isNotEmpty()) {
        communityCards.add(deck.removeAt(0))
    }
}

@Composable
fun ControlButton(label: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        color = Color.Gray.copy(alpha = 0.8f),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

data class PlayerData(val playerName: String, val betAmount: String, val profileImageRes: Int)

fun getPlayerPosition(index: Int, numberOfPlayers: Int): Alignment {
    return when (index) {
        0 -> Alignment.TopCenter
        1 -> Alignment.TopStart
        2 -> Alignment.TopEnd
        3 -> Alignment.CenterStart
        4 -> Alignment.CenterEnd
        5 -> Alignment.BottomStart
        6 -> Alignment.BottomEnd
        7 -> Alignment.BottomCenter
        else -> Alignment.Center
    }
}
