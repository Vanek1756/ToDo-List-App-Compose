package com.example.todolist.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.todolist.MainNavOption
import com.example.todolist.R
import com.example.todolist.mainGraph
import com.example.todolist.ui.components.appdrawer.AppDrawerContent
import com.example.todolist.ui.components.appdrawer.AppDrawerItemInfo

sealed class Screen(val route: String) {
    object Home : Screen("home")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListApp(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    Surface {

        // the main drawer composable, which creates the actual drawer
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                AppDrawerContent(
                    drawerState = drawerState,
                    menuItems = DrawerParams.drawerButtons,
                    defaultPick = MainNavOption.Home
                ) { onUserPickedOption ->
                    // when user picks, the path - navigates to new one
                    when (onUserPickedOption) {
                        MainNavOption.Home -> {
                            navController.navigate(onUserPickedOption.name) {
                                // pops the route to root and places new screen
                                popUpTo(MainNavOption.Home.name)
                            }
                        }

                        MainNavOption.Categories -> {
                            navController.navigate(onUserPickedOption.name) {
                                popUpTo(MainNavOption.Home.name)
                            }
                        }

                        else -> {

                        }
                    }
                }
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = "MainRoute"
            ) {
                mainGraph(drawerState, navController)
            }
        }
    }
}

// list of the buttons
object DrawerParams {
    val drawerButtons = arrayListOf(
        AppDrawerItemInfo(
            MainNavOption.Home,
            R.string.menu_home,
            Icons.Default.Menu,
            null,
            R.string.menu_home
        ),
        AppDrawerItemInfo(
            MainNavOption.Categories,
            R.string.menu_categories,
            null,
            R.drawable.ic_categories,
            R.string.menu_categories
        ),
    )
}