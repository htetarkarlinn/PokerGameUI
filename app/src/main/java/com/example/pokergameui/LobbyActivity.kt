package com.example.pokergameui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
class LobbyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showDialog by remember { mutableStateOf(false) } // Manage dialog visibility

            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text("Game Lobby", color = Color.White) },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color(0xFF00796B)
                        )
                    )
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    AvatarSection(chipBalance = 5000) // Pass player's chip balance
                    Spacer(modifier = Modifier.height(16.dp))
                    ScoreboardSection()
                    Spacer(modifier = Modifier.height(16.dp))
                    FriendListSection()
                    Spacer(modifier = Modifier.height(16.dp))
                    PlayButtonSection(
                        onCreateTableClicked = {
                            startActivity(Intent(this@LobbyActivity, CreateActivity::class.java))

                        },
                        onJoinTableClicked = {
                            startActivity(Intent(this@LobbyActivity, JoinActivity::class.java))

                        },
                        onClickGameRule = {
                            val youtubeUrl = "https://www.youtube.com/watch?v=JOomXP-r1wY" // Replace with your YouTube URL
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
                            startActivity(intent)
                        }
                    )
                }
            }


        }
    }
}


@Composable
fun AvatarSection(chipBalance: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Replace with actual avatar image
        Surface(
            modifier = Modifier.size(80.dp),
            shape = RoundedCornerShape(40.dp),
            color = Color.Gray
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("Avatar", color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Player Name",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        // Display chip balance
        Text(
            text = "Chip Balance: $chipBalance",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF00796B) // Highlighted chip balance color
        )
    }
}

@Composable
fun ScoreboardSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Scoreboard",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        repeat(3) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Player ${it + 1}", fontSize = 16.sp)
                Text("${(1000 * (it + 1))} Chips", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun FriendListSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Friend List",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        repeat(3) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Friend ${it + 1}", fontSize = 16.sp)
                Button(onClick = { /* Invite friend logic */ }) {
                    Text("Invite")
                }
            }
        }
    }
}


@Composable
fun PlayButtonSection( onCreateTableClicked: () -> Unit, onJoinTableClicked: () -> Unit, onClickGameRule: () -> Unit  ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { onJoinTableClicked()  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00796B),
                contentColor = Color.White
            )
        ) {
            Text("Join Poker", fontSize = 16.sp)
        }

        Button(
            onClick = onCreateTableClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00796B),
                contentColor = Color.White
            )
        ) {
            Text("Create Poker", fontSize = 16.sp)
        }

        Button(
            onClick = { onClickGameRule() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00796B),
                contentColor = Color.White
            )
        ) {
            Text("Game Rule", fontSize = 16.sp)
        }
    }
}