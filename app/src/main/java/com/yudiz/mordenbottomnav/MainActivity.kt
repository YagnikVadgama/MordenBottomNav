package com.yudiz.mordenbottomnav

import android.os.Build
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yudiz.mordenbottomnav.ui.theme.MordenBottomNavTheme

class MainActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            /*            val navController = rememberNavController()
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
            }*/

            val navController = rememberNavController()

            window.statusBarColor=MaterialTheme.colors.primary.toArgb()
            window.navigationBarColor= MaterialTheme.colors.background.toArgb()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                window.navigationBarDividerColor=MaterialTheme.colors.onBackground.copy(alpha = 0.1f).toArgb()
            }

            val currentScreen= mutableStateOf<Screens>(Screens.Home)

            MordenBottomNavTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    Scaffold(
                        bottomBar = {
                            MainFunc(route = currentScreen.value.route) {
                                currentScreen.value = it
                                navController.navigate(it.route)
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