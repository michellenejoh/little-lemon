package com.littlelemon.littlelemon

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.littlelemon.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val sharedPreferences = getSharedPreferences("LittleLemon", MODE_PRIVATE)
        setContent {
            LittleLemonTheme {
               MyNavigation(sharedPreferences=sharedPreferences)
            }
        }
    }
}

@Composable
fun MyNavigation(sharedPreferences: SharedPreferences) {
    val navController = rememberNavController()
    NavigationComposable(navController = navController, sharedPreferences = sharedPreferences)
}
