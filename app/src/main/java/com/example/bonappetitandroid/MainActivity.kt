package com.example.bonappetitandroid

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bonappetitandroid.ui.screens.ProfileScreen
import com.example.linguaflow.R
import com.example.restaurantandroid.ui.screens.*
import kotlinx.coroutines.delay


val showDialog = mutableStateOf(-1)
val colorText = mutableStateOf(0)
val selectedBottomBar = mutableStateOf("home")
var home = mutableStateOf(true)
var basket = mutableStateOf(false)
var profile = mutableStateOf(false)
val eat = Eat.Food().food
val test = mutableStateOf(true)
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        val window: Window = window //скрыть шторку уведомлений

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        val decorView = getWindow().decorView //скрыть панель навигации

        val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_IMMERSIVE)
        decorView.systemUiVisibility = uiOptions

        setContent {
            Scaffold(
                backgroundColor = colorResource(R.color.black)
            ) {
                Navigation()
            }
        }
    }
}



@Composable
fun BottomNavigationBar() {

    val items = listOf(
        NavHostController.home,
        NavHostController.basket,
        NavHostController.profile,
    )
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.nav), contentColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(icon = {
                Icon(
                    painterResource(id = item.icon),
                    contentDescription = item.title,
                    modifier = Modifier.size(25.dp)
                )
            },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
                alwaysShowLabel = true,
                selected = item.route == selectedBottomBar.value,
                onClick = {
                    if (item.title == "Дом") {
                        profile.value = false
                        basket.value = false
                        home.value = true
                        selectedBottomBar.value = "home"
                    }
                    if (item.title == "Корзина") {
                        home.value = false
                        profile.value = false
                        basket.value = true
                        selectedBottomBar.value = "basket"
                    }
                    if (item.title == "Профиль") {
                        home.value = false
                        basket.value = false
                        profile.value = true
                        selectedBottomBar.value = "profile"
                    }

                }
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        // Main Screen
        composable("main_screen") {
            Scaffold(
                bottomBar = { BottomNavigationBar() },
                backgroundColor = colorResource(R.color.black)
            ) {
                HomeScreen()
                BasketScreen()
                ProfileScreen()
            }
        }
    }
}



@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 3000,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(3000L)
        navController.navigate("main_screen")
    }

    // Image
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.logo_app),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value))
    }
}

