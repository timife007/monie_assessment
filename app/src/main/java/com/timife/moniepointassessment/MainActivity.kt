package com.timife.moniepointassessment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.timife.moniepointassessment.presentation.ScreenRoute
import com.timife.moniepointassessment.presentation.calculate.CalculateScreen
import com.timife.moniepointassessment.presentation.homescreen.HomeScreen
import com.timife.moniepointassessment.presentation.profile.ProfileScreen
import com.timife.moniepointassessment.presentation.shipment.ShipmentScreen
import com.timife.moniepointassessment.presentation.success.SuccessScreen
import com.timife.moniepointassessment.ui.theme.MoniepointAssessmentTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MoniepointAssessmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }) {
                        NavigationScreens(
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

val items = listOf(
    ScreenRoute.HomeScreen,
    ScreenRoute.CalculateScreen,
    ScreenRoute.ShipmentScreen,
    ScreenRoute.ProfileScreen
)

@Composable
fun NavigationScreens(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.HomeScreen.route,
        enterTransition = {
            fadeIn(
                animationSpec = spring(
                    Spring.DampingRatioNoBouncy,
                    Spring.StiffnessVeryLow
                )
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = spring(
                    Spring.DampingRatioNoBouncy,
                    Spring.StiffnessVeryLow
                )
            )
        }
    ) {
        composable(ScreenRoute.HomeScreen.route) {
            HomeScreen(Modifier)
        }
        composable(ScreenRoute.SearchScreen.route) {

        }
        composable(ScreenRoute.ShipmentScreen.route) {
            ShipmentScreen(navController = navController)
        }
        composable(ScreenRoute.CalculateScreen.route) {
            CalculateScreen(navController)
        }
        composable(ScreenRoute.SuccessScreen.route) {
            SuccessScreen(modifier = Modifier, navController)
        }
        composable(ScreenRoute.ProfileScreen.route) {
            ProfileScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navItems = items
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    var previousSelectedItem by rememberSaveable { mutableIntStateOf(0) }
    val hideBottomNavRoutes = listOf(
        ScreenRoute.SuccessScreen.route,
        ScreenRoute.CalculateScreen.route,
        ScreenRoute.ShipmentScreen.route
    )

    var isVisible by remember { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(currentRoute) {
        currentRoute?.let { route ->
            val currentIndex = navItems.indexOfFirst { it.route == route }
            if (currentIndex >= 0) {
                previousSelectedItem = selectedItem
                selectedItem = currentIndex
            }
        }
    }

    /**
     * Used disposable effect to cancel any side effect that might occur
     * as a result of quick changes to current route before 2 seconds elapses
     * as the coroutine might still be running thereby falsely setting isVisible to false
     * hence, cancelling the job when there's a change helps.
     */

    DisposableEffect(currentRoute) {
       val job =  if (currentRoute in hideBottomNavRoutes) {
            coroutineScope.launch {
                // Delay hiding BottomNavigationBar for 2 seconds
                delay(200)
                isVisible = false
            }
        } else {
            isVisible = true
           null
        }
        onDispose {
            job?.cancel()
        }
    }
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(durationMillis = 800)
        ),
        exit = slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(durationMillis = 800)
        )
    ) {
        NavigationBar(
            modifier = Modifier
                .height(60.dp)
                .shadow(20.dp),
            containerColor = MaterialTheme.colorScheme.background
        ) {
            navItems.forEachIndexed { index, screenRoute ->
                val isSelected = currentRoute == screenRoute.route
                val slideDirection = if (index >= previousSelectedItem) -1 else 1

                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                    modifier = Modifier.fillMaxHeight(),
                    selected = isSelected,
                    onClick = {
                        previousSelectedItem = selectedItem
                        selectedItem = index
                        navController.navigate(screenRoute.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) { saveState = true }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            AnimatedVisibility(
                                visible = isSelected,
                                enter = slideInHorizontally(
                                    initialOffsetX = { slideDirection * it },
                                    animationSpec = tween(durationMillis = 200)
                                ),
                                exit = fadeOut(animationSpec = tween(durationMillis = 200))
                            ) {
                                Divider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(3.5.dp),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Icon(
                                imageVector = screenRoute.icon,
                                contentDescription = null,
                                tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
                            )
                            Text(
                                text = screenRoute.label,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
                                )
                            )
                        }
                    },
                    label = {
                    }
                )
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoniepointAssessmentTheme {
        Greeting("Android")
    }
}