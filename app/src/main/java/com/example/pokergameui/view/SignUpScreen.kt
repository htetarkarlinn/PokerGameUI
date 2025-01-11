package com.example.pokergameui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokergameui.ui.theme.Blue
import com.example.pokergameui.ui.theme.InputLabel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(onBackPressed: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sign Up") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SignUpHeader()
            Spacer(modifier = Modifier.height(16.dp))
            SignUpForm(modifier = Modifier.fillMaxWidth())
        }
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
        InputLabel(
            label = "Phone Number",
            placeholder = "Enter your phone number",
            modifier = modifier
        )
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
