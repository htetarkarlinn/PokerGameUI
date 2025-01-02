package com.example.pokergameui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class CreateActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text("Create Poker Table", color = Color.White) },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color(0xFF00796B)
                        )
                    )
                }
            ) { padding ->
                CreateBody(
                    Modifier.padding(padding),
                    onCreateTable = { tableName, numberOfPlayers, minBet ->
                        // Pass table details to InGameActivity
                        val intent = Intent(this, InGameActivity::class.java).apply {
                            putExtra("TABLE_NAME", tableName)
                            putExtra("NUMBER_OF_PLAYERS", numberOfPlayers)
                            putExtra("MIN_BET", minBet)
                        }
                        startActivity(intent)
                        finish() // Close CreateActivity after table creation
                    }
                )
            }
        }
    }
}

@Composable
fun CreateBody(
    modifier: Modifier = Modifier,
    onCreateTable: (String, Int, Int) -> Unit
) {
    var tableName by remember { mutableStateOf("") }
    var numberOfPlayers by remember { mutableStateOf("") }
    var minBet by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val players = numberOfPlayers.toIntOrNull() ?: 0
                val bet = minBet.toIntOrNull() ?: 0
                onCreateTable(tableName, players, bet)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
        ) {
            Text("Create Table", color = Color.White, fontSize = 16.sp)
        }
    }
}
