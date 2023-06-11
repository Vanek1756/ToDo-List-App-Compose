package com.example.todolist

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.todolist.ui.categories.CategoriesScreen
import com.example.todolist.ui.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.mainGraph(drawerState: DrawerState) {
    navigation(startDestination = MainNavOption.Home.name, route = "MainRoute") {
        composable(MainNavOption.Home.name){
            HomeScreen(drawerState)
        }
        composable(MainNavOption.Categories.name){
            CategoriesScreen(drawerState)
        }
    }
}

enum class MainNavOption {
    Home,
    Categories,
}