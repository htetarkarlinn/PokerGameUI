package com.example.pokergameui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pokergameui.ui.theme.PokerGameUITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LobbyScreen(navController: NavHostController) {
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
                navController = navController,
                onCreateTableClicked = {

                    showDialog = true
                }
            )
        }
    }

    // Show PokerTableDialog when showDialog is true
    if (showDialog) {
        PokerTableDialog(
            onDismiss = { showDialog = false },
            onCreate = { tableName, numberOfPlayers, minBet ->
                // Handle table creation logic here
                showDialog = false
            }
        )
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
fun PokerTableDialog(
    onDismiss: () -> Unit,
    onCreate: (String, Int, Int) -> Unit
) {
    var tableName by remember { mutableStateOf("") }
    var numberOfPlayers by remember { mutableStateOf("") }
    var minBet by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Create Poker Table", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        },
        text = {
            Column {
                OutlinedTextField(
                    value = tableName,
                    onValueChange = { tableName = it },
                    label = { Text("Table Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = numberOfPlayers,
                    onValueChange = { numberOfPlayers = it },
                    label = { Text("Number of Players") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = minBet,
                    onValueChange = { minBet = it },
                    label = { Text("Min Bet") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val players = numberOfPlayers.toIntOrNull() ?: 0
                    val bet = minBet.toIntOrNull() ?: 0
                    onCreate(tableName, players, bet)
                }
            ) {
                Text("Create")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun PlayButtonSection(
    navController: NavHostController,
    onCreateTableClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.navigate("poker_table") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00796B),
                contentColor = Color.White
            )
        ) {
            Text("Join a Poker Table", fontSize = 16.sp)
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
            Text("Create a Poker Table", fontSize = 16.sp)
        }
    }
}

