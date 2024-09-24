package com.barisscebeci.tadal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.barisscebeci.tadal.ui.theme.TadAlTheme
import com.barisscebeci.tadal.presentation.ui.screen.Transitions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TadAlTheme {
                val navController = rememberNavController()
                Transitions(navController = navController)
            }
        }
    }
}


