package com.example.pokergameui.view

import android.util.Log
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun LobbyScreen(
    navController: NavController,
    onCreateTableClicked: () -> Unit,
    onJoinTableClicked: () -> Unit,
    onClickGameRule: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        AvatarSection(chipBalance = 1000)
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(
                onClick = { navController.navigate("scoreboard") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
            ) {
                Text("Scoreboard", color = Color.White)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { navController.navigate("friend_list") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
            ) {
                Text("Friends", color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        PlaySection(
            rememberNavController(),
            onCreateTableClicked = onCreateTableClicked,
            onJoinTableClicked = onJoinTableClicked,
            onClickGameRule = onClickGameRule
        )
    }
}

@Composable
fun CreateTableScreen(onCreateTable: (String, Int, Int) -> Unit) {
    var tableName by remember { mutableStateOf("") }
    var numberOfPlayers by remember { mutableStateOf("") }
    var minBet by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
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

@Composable
fun JoinTableScreen(navController: NavController) {
    val tables = remember { generateDummyTables() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        PokerTableList(tables)
    }
}


@Composable
fun PokerTableList(tables: List<PokerTable>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(tables.size) { index ->
            PokerTableItem(tables[index])
        }
    }
}

@Composable
fun PokerTableItem(table: PokerTable) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                Log.d(
                    "PokerTableClick",
                    "Clicked on Table: ${table.tableName}, Players: ${table.currentPlayer}/${table.maxPlayer}, Min Bet: ${table.minBet}"
                )
            },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2F1))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Table: ${table.tableName}", fontSize = 16.sp, color = Color.Black)
            Text(
                "Players: ${table.currentPlayer}/${table.maxPlayer}",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
            Text("Minimum Bet: ${table.minBet} Chips", fontSize = 14.sp, color = Color.DarkGray)
        }
    }
}

fun generateDummyTables(): List<PokerTable> {
    return listOf(
        PokerTable("Table 1", 3, 6, 50),
        PokerTable("Table 2", 5, 8, 100),
        PokerTable("Table 3", 2, 4, 25),
        PokerTable("Table 4", 6, 6, 200),
        PokerTable("Table 5", 1, 4, 10)
    )
}

@Composable
fun FriendListScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Friend List",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        repeat(3) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Friend ${it + 1}")
                Button(onClick = { /* Invite Friend Logic */ }) {
                    Text("Invite")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        Button(
            onClick = { navController.navigate("play") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
        ) {
            Text("Go to Play Options", color = Color.White)
        }
    }
}

@Composable
fun PlaySection(
    navController: NavController,
    onCreateTableClicked: () -> Unit,
    onJoinTableClicked: () -> Unit,
    onClickGameRule: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onCreateTableClicked,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
        ) {
            Text("Create Table", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onJoinTableClicked,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
        ) {
            Text("Join Table", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onClickGameRule,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
        ) {
            Text("Game Rules", color = Color.White)
        }
    }
}

@Composable
fun ScoreboardScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Scoreboard",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        repeat(3) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Player ${it + 1}")
                Text(text = "${(it + 1) * 1000} Chips")
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun AvatarSection(chipBalance: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Surface(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(40.dp),
                color = Color.Gray
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("Avatar", color = Color.White)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Player Name",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Chip Balance: $chipBalance",
                    fontSize = 16.sp,
                    color = Color(0xFF00796B)
                )
            }
        }
    }
}
