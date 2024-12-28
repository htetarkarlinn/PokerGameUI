package com.example.pokergameui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokergameui.ui.theme.InputLabel
import com.example.pokergameui.ui.theme.Blue
import com.example.pokergameui.ui.theme.PokerGameUITheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokerGameUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignUpBody(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SignUpBody(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignUpHeader()
        Spacer(modifier = Modifier.height(16.dp))
        SignUpForm(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun SignUpHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sign Up",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Create an account to continue",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun SignUpForm(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(vertical = 16.dp)) {
        InputLabel(label = "Full Name", placeholder = "Enter your full name", modifier = modifier)
        Spacer(modifier = Modifier.height(8.dp))
        InputLabel(label = "Email", placeholder = "Enter your email", modifier = modifier)
        Spacer(modifier = Modifier.height(8.dp))
        InputLabel(label = "Phone Number", placeholder = "Enter your phone number", modifier = modifier)
        Spacer(modifier = Modifier.height(8.dp))
        InputLabel(label = "Username", placeholder = "Enter your username", modifier = modifier)
        Spacer(modifier = Modifier.height(8.dp))
        InputLabel(
            label = "Password",
            placeholder = "Enter your password",
            hidden = true,
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(8.dp))
        InputLabel(
            label = "Confirm Password",
            placeholder = "Confirm your password",
            hidden = true,
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* TODO: Handle Sign Up Logic */ },
            colors = ButtonDefaults.buttonColors(containerColor = Blue),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Sign Up",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8")
@Composable
fun SignUpPreview() {
    PokerGameUITheme {
        SignUpBody()
    }
}
