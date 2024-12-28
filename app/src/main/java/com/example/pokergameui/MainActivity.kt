package com.example.pokergameui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.pokergameui.ui.theme.PokerGameUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokerGameUITheme {
                val navController = rememberNavController()
                NavigationComponent(navController)
            }
        }
    }
}
