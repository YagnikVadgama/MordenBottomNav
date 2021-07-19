package com.yudiz.mordenbottomnav

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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

            window.statusBarColor = MaterialTheme.colors.primary.toArgb()
            window.navigationBarColor = MaterialTheme.colors.background.toArgb()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                window.navigationBarDividerColor =
                    MaterialTheme.colors.onBackground.copy(alpha = 0.1f).toArgb()
            }

            val currentScreen = mutableStateOf<Screens>(Screens.Home)

            MordenBottomNavTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(text = "Bottom Navigation") },
                                actions = {

                                },
                                backgroundColor = Color.Blue
                            )
                        },
                        bottomBar = {
                            MainFunc(route = currentScreen.value.route) {
                                navController.graph.startDestinationRoute?.let { items ->
                                    navController.popBackStack(
                                        items, false
                                    )
                                }
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

@Composable
fun ScreenController(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            Home()
        }
        composable("Profile") {
            Profile()
        }
        composable("Offers") {
            Offers()
        }
        composable("About") {
            About()
        }
        composable("Setting") {
            Setting()
        }
    }
}