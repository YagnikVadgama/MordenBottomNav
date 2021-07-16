package com.yudiz.mordenbottomnav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.rememberNavController
import com.yudiz.mordenbottomnav.ui.theme.MordenBottomNavTheme

class MainActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val currentScreen = mutableStateOf<Screens>(Screens.Home)

            MordenBottomNavTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        bottomBar = {
                            MainFunc(route = currentScreen.value.route) {
                                currentScreen.value = it
                            }
                        }
                    ) {
                        ScreenController(navController = navController)
                    }
                }
            }
        }
    }
}