package com.example.drawernavigationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.drawernavigationdemo.ui.screens.AccountScreen
import com.example.drawernavigationdemo.ui.screens.HelpScreen
import com.example.drawernavigationdemo.ui.screens.HomeScreen
import com.example.drawernavigationdemo.ui.theme.DrawerNavigationDemoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrawerNavigationDemoTheme {
                MainScreenComposable()
            }
        }
    }
}

sealed class DrawerScreens(val title: String, val route: String) {
    object Home : DrawerScreens("Home", "home")
    object Account : DrawerScreens("Account", "account")
    object Help : DrawerScreens( "Help", "help")
}

private val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.Account,
    DrawerScreens.Help
)

@Composable
fun MainScreenComposable() {
    val navController= rememberNavController()
    Surface(
        color = MaterialTheme.colors.background
    ) {
        // we need to have drawer state
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var openDrawer = {
            scope.launch {
                drawerState.open()
            }
        }

        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                //construct the drawer here
                DrawerItem(onDestinationCilcked = {
                    route -> scope.launch {
                        drawerState.close()
                }
                    navController.navigate(route) {
                        navController.graph.startDestinationRoute?.let { popUpTo(it) }
                        launchSingleTop = true
                    }
                })
            }
        ) {
            //create the nav host here
            NavHost(
                navController = navController,
                startDestination = DrawerScreens.Home.route ) {
                composable(DrawerScreens.Home.route) {
                    HomeScreen (
                        openDrawer = {
                            openDrawer()
                        }
                    )

                }
                composable(DrawerScreens.Account.route) {
                    AccountScreen (
                        openDrawer = {
                            openDrawer()
                        }
                    )
                }
                composable(DrawerScreens.Help.route) {
                    HelpScreen(navController = navController)
                }
            }
        }

    }
}

@Composable
fun DrawerItem(
    modifier: Modifier = Modifier,
    onDestinationCilcked: (route:String) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_baseline_home_24) ,
            contentDescription = "Profile pic" ,
            modifier = Modifier.size(30.dp),
            contentScale = ContentScale.Crop
        )

        screens.map {
                item->
            Spacer(modifier = modifier.height(24.dp))
            Text(
                text = item.title,
            modifier= Modifier.clickable {
                onDestinationCilcked(item.route)
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DrawerNavigationDemoTheme {
        MainScreenComposable()
    }
}