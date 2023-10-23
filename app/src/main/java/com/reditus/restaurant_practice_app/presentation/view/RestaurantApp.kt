package com.reditus.restaurant_practice_app.presentation.view

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.reditus.restaurant_practice_app.presentation.common.layout.DefaultLayout
import com.reditus.restaurant_practice_app.presentation.common.navigation.MyAppHost
import com.reditus.restaurant_practice_app.presentation.common.navigation.Router


@Composable
fun RestaurantApp(){
    val navController = rememberNavController()
    val items = listOf(
        Router.RESTAURANT_LIST,
        Router.PRODUCT_LIST,
        Router.ORDER_LIST,
        Router.PROFILE
    )
    DefaultLayout(
        bottomBar = {

            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screen.name } == true,
                        onClick = {
                            navController.navigate(screen.name){
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Text(text = " ${screen.korean}")
                        }
                    )
                }
            }


        }
    ) {
        MyAppHost(navController = navController)
    }
}