/*package com.example.pokergameui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class InGameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            InGameScreen(this)
        }
    }
}

@Composable
fun InGameScreen(activity: Activity) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background image of poker table
        Image(
            painter = painterResource(id = R.drawable.img_background), // Replace with your poker table drawable
            contentDescription = "Poker Table",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        CloseImageExample(activity = activity)

        // Players (circles around the table)
        PlayerPositions()

        // Card holders in the center
        CardHolders()

    }
}

@Composable
fun CloseImageExample(activity: Activity) {
    Box(
        contentAlignment = Alignment.TopStart, // Align close button to top-right
        modifier = Modifier.fillMaxSize()
    ) {
        // Close button with image
        Image(
            painter = painterResource(id = R.drawable.ic_close), // Replace with your image resource
            contentDescription = "Close",
            modifier = Modifier
                .size(70.dp) // Set the size of the close button
                .clickable {
                    activity.finish() // End the activity on click
                }
                .padding(16.dp) // Add padding to the image
        )
    }
}

@Composable
fun PlayerPositions() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Player 1 (Top Center)
        PlayerBox(
            playerName = "P1",
            betAmount = "340",
            profileImageRes = R.drawable.p6,
            modifier = Modifier
                .offset(y = 8.dp)
                .align(Alignment.TopCenter)
        )
        // Top Left Player (P2)
        PlayerBox(
            playerName = "P2",
            betAmount = "85",
            profileImageRes = R.drawable.p2,
            modifier = Modifier
                .offset(x = 100.dp, y = 16.dp)
                .align(Alignment.TopStart)
        )
        // Player 3 (Top Right)
        PlayerBox(
            playerName = "P3",
            betAmount = "99",
            profileImageRes = R.drawable.p3,
            modifier = Modifier
                .offset(x = -100.dp, y = 16.dp)
                .align(Alignment.TopEnd)
        )
        // Player 4 (Middle Left)
        PlayerBox(
            playerName = "P4",
            betAmount = "10",
            profileImageRes = R.drawable.p1,
            modifier = Modifier
                .offset(x = 0.dp)
                .align(Alignment.CenterStart)
        )
        // Player 5 (Middle Right)
        PlayerBox(
            playerName = "P5",
            betAmount = "56",
            profileImageRes = R.drawable.p2,
            modifier = Modifier
                .offset(x = 0.dp)
                .align(Alignment.CenterEnd)
        )
        // Player 6 (Bottom Left)
        PlayerBox(
            playerName = "P6",
            betAmount = "5000",
            profileImageRes = R.drawable.p5,
            modifier = Modifier
                .offset(x = 100.dp, y = -8.dp)
                .align(Alignment.BottomStart)
        )
        // Player 7 (Bottom Right)
        PlayerBox(
            playerName = "P7",
            betAmount = "150",
            profileImageRes = R.drawable.p6,
            modifier = Modifier
                .offset(x = -100.dp, y = -8.dp)
                .align(Alignment.BottomEnd)
        )
        // Player 8 (Bottom Center)
        PlayerBox(
            playerName = "P8",
            betAmount = "50",
            profileImageRes = R.drawable.p1,
            modifier = Modifier
                .offset(y = -8.dp)
                .align(Alignment.BottomCenter)
        )
    }

}

@Composable
fun PlayerBox(playerName: String, betAmount: String, profileImageRes: Int, modifier: Modifier) {
    if (playerName == "P8"){
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { },
                    border = BorderStroke(width = 1.dp, color = Color.Red),
                    modifier = Modifier
                        .width(85.dp)
                        .height(35.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "Fold", color = Color.Red, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = { },
                    border = BorderStroke(width = 1.dp, color = Color.Yellow),
                    modifier = Modifier
                        .width(85.dp)
                        .height(35.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "Call", color = Color.Yellow, fontWeight = FontWeight.Bold)
                }


            }

            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                OverlappingImages(
                    imageRes1 = R.drawable.ten_of_clubs, // Replace with your first image resource
                    imageRes2 = R.drawable.three_of_hearts  // Replace with your second image resource
                )

                Spacer(modifier = Modifier.height(4.dp))

                Button(
                    onClick = { },
                    border = BorderStroke(width = 1.dp, color = Color.Blue),
                    modifier = Modifier
                        .width(85.dp)
                        .height(35.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "All In", color = Color.Blue, fontWeight = FontWeight.Bold)
                }
            }

            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { },
                    border = BorderStroke(width = 1.dp, color = Color.Gray),
                    modifier = Modifier
                        .width(85.dp)
                        .height(35.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "Up", color = Color.White, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = { },
                    border = BorderStroke(width = 1.dp, color = Color.Gray),
                    modifier = Modifier
                        .width(85.dp)
                        .height(35.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "Down", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }

        }
    }else{
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Profile image
            Surface(
                shape = CircleShape,
                modifier = Modifier.size(50.dp),
                color = Color.Gray.copy(alpha = 0.8f)
            ) {
                Image(
                    painter = painterResource(id = profileImageRes), // Replace with actual drawable
                    contentDescription = "$playerName Profile Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Player name
            Text(
                text = playerName,
                fontSize = 14.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Bet amount
            Text(
                text = betAmount,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Yellow,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }

}

@Composable
fun CardHolders() {
    Box(
        modifier = Modifier
            .fillMaxSize(), // Fill the entire screen
        contentAlignment = Alignment.Center // Center the card holders within the Box
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .size(650.dp, 130.dp)
                .background(color = Color.Transparent)
                .border(5.dp, Color.Black, RoundedCornerShape(100.dp)) // Border with rounded corners
                .wrapContentWidth() // Ensure only the content width is taken
        ) {
            repeat(5) {
                Surface(
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .size(50.dp, 70.dp), // Adjust size as needed
                    shape = RoundedCornerShape(8.dp), // Optional: Rounded corners
                    border = BorderStroke(2.dp, Color.Gray), // Gray border
                    color = Color.Transparent // Transparent background
                ) {
                    // Empty Surface, no content inside
                }
            }
        }
    }
}

@Composable
fun OverlappingImages(imageRes1: Int, imageRes2: Int) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        // First Image (background image)
        Image(
            painter = painterResource(id = imageRes1),
            contentDescription = "Image 1",
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .border(width = 0.5.dp, color = Color.Black)
                .background(Color.White)
        )

        // Second Image (overlapping image)
        Image(
            painter = painterResource(id = imageRes2),
            contentDescription = "Image 2",
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .padding(start = 30.dp)
                .border(width = 0.5.dp, color = Color.Black)
                .background(Color.White)
        )
    }
}*/


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
        CardHolders()
    }
}

@Composable
fun CloseImageExample(activity: Activity) {
    Box(
        contentAlignment = Alignment.TopStart, // Align close button to top-right
        modifier = Modifier.fillMaxSize()
    ) {
        // Close button with image
        Image(
            painter = painterResource(id = R.drawable.ic_close), // Replace with your image resource
            contentDescription = "Close",
            modifier = Modifier
                .size(70.dp) // Set the size of the close button
                .clickable {
                    activity.finish() // End the activity on click
                }
                .padding(16.dp) // Add padding to the image
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
        // Profile image
        Surface(
            shape = CircleShape,
            modifier = Modifier.size(50.dp),
            color = Color.Gray.copy(alpha = 0.8f)
        ) {
            Image(
                painter = painterResource(id = profileImageRes), // Replace with actual drawable
                contentDescription = "$playerName Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Player name
        Text(
            text = playerName,
            fontSize = 14.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Bet amount
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
fun CardHolders() {
    Box(
        modifier = Modifier
            .fillMaxSize(), // Fill the entire screen
        contentAlignment = Alignment.Center // Center the card holders within the Box
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .size(650.dp, 130.dp)
                .background(color = Color.Transparent)
                .border(5.dp, Color.Black, RoundedCornerShape(100.dp)) // Border with rounded corners
                .wrapContentWidth() // Ensure only the content width is taken
        ) {
            repeat(5) {
                Surface(
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .size(50.dp, 70.dp), // Adjust size as needed
                    shape = RoundedCornerShape(8.dp), // Optional: Rounded corners
                    border = BorderStroke(2.dp, Color.Gray), // Gray border
                    color = Color.Transparent // Transparent background
                ) {
                    // Empty Surface, no content inside
                }
            }
        }
    }
}
@Composable
fun OverlappingImages(imageRes1: Int, imageRes2: Int) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        // First Image (background image)
        Image(
            painter = painterResource(id = imageRes1),
            contentDescription = "Image 1",
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .border(width = 0.5.dp, color = Color.Black)
                .background(Color.White)
        )

        // Second Image (overlapping image)
        Image(
            painter = painterResource(id = imageRes2),
            contentDescription = "Image 2",
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .padding(start = 30.dp)
                .border(width = 0.5.dp, color = Color.Black)
                .background(Color.White)
        )
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
        else -> Alignment.Center // Fallback position
    }
}


