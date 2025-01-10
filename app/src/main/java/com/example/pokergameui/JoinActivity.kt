package com.example.pokergameui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data class for table details
data class PokerTable(
    val tableName: String,
    val currentPlayer: Int,
    val maxPlayer: Int,
    val minBet: Int
)

class JoinActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text("Join Poker Table", color = Color.White) },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color(0xFF00796B)
                        )
                    )
                }
            ) { padding ->
                JoinPokerBody(Modifier.padding(padding))
            }
        }
    }
}

@Composable
fun JoinPokerBody(modifier: Modifier = Modifier) {
    val tables = remember { generateDummyTables() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Scrollable table list
        PokerTableList(tables.sortedBy { it.currentPlayer })
    }
}

// Composable to display a scrollable list of poker tables
@Composable
fun PokerTableList(tables: List<PokerTable>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight() // Fill available vertical space
            .padding(8.dp)
    ) {
        items(tables.size) { index ->
            PokerTableItem(tables[index])
        }
    }
}

// Composable to display a single poker table item
@Composable
fun PokerTableItem(table: PokerTable) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // Log a message with the table details when the card is clicked
                Log.d(
                    "PokerTableClick",
                    "Clicked on Table: ${table.tableName}, Players: ${table.currentPlayer}/${table.maxPlayer}, Min Bet: ${table.minBet}"
                )
            },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2F1))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Table: ${table.tableName}", fontSize = 16.sp, color = Color.Black)
            Text("Players: ${table.currentPlayer}/${table.maxPlayer}", fontSize = 14.sp, color = Color.DarkGray)
            Text("Minimum Bet: ${table.minBet} Chips", fontSize = 14.sp, color = Color.DarkGray)
        }
    }
}

// Generate dummy data for testing
fun generateDummyTables(): List<PokerTable> {
    return listOf(
        PokerTable("Table 1", 3, 6, 50),
        PokerTable("Table 2", 5, 8, 100),
        PokerTable("Table 3", 2, 4, 25),
        PokerTable("Table 4", 6, 6, 200),
        PokerTable("Table 5", 1, 4, 10),
        PokerTable("Table 6", 4, 5, 75),
        PokerTable("Table 7", 3, 7, 30),
        PokerTable("Table 8", 2, 6, 20),
        PokerTable("Table 9", 5, 8, 150),
        PokerTable("Table 10", 1, 3, 5)
    )
}
