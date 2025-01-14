package com.example.pokergameui.view

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokergameui.ui.theme.PokerGameUITheme

class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PokerGameUITheme {
                NavigationHost(onOrientationChange = { isLandscape ->
                    requestedOrientation = if (isLandscape) {
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    } else {
                        ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                    }
                })
            }
        }
    }
}


@Composable
fun NavigationHost(onOrientationChange: (Boolean) -> Unit) {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "start_screen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("start_screen") {
                LaunchedEffect(Unit) {
                    onOrientationChange(true)
                }
                StartScreen(
                    handleSignIn = {
                        navController.navigate("signin_screen")
                        onOrientationChange(false)
                    },
                    handleSignUp = {
                        navController.navigate("signup_screen")
                        onOrientationChange(false)
                    }
                )
            }
            composable("signin_screen") {
                LaunchedEffect(Unit) {
                    onOrientationChange(false)
                }
                SignInScreen(onBackPressed = { navController.popBackStack() })
            }
            composable("signup_screen") {
                LaunchedEffect(Unit) {
                    onOrientationChange(false)
                }
                SignUpScreen(onBackPressed = { navController.popBackStack() })
            }
        }
    }
}
