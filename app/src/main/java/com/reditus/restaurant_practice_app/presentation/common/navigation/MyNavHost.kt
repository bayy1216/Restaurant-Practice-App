package com.reditus.restaurant_practice_app.presentation.common.navigation


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.reditus.restaurant_practice_app.presentation.view.order.OrderScreen
import com.reditus.restaurant_practice_app.presentation.view.product.ProductScreen
import com.reditus.restaurant_practice_app.presentation.view.restaurant.RestaurantDetailScreen
import com.reditus.restaurant_practice_app.presentation.view.restaurant.RestaurantScreen
import com.reditus.restaurant_practice_app.presentation.view.user.LoginScreen
import com.reditus.restaurant_practice_app.presentation.view.user.ProfileScreen
import com.reditus.restaurant_practice_app.presentation.viewmodel.user.UserViewModel

enum class Router(val korean: String) {
    LOGIN("로그인"),
    RESTAURANT_LIST("식당 목록"),
    RESTAURANT_DETAIL(""),
    PRODUCT_LIST("메뉴"),
    ORDER_LIST("주문"),
    PROFILE("프로필")
}

@Composable
fun MyAppHost(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = "root_tab"
    ){
        navigation(
            startDestination = Router.RESTAURANT_LIST.name,
            route = "root_tab"
        ){
            composable(Router.RESTAURANT_LIST.name){
                RestaurantScreen(navController)
            }
            composable(Router.PRODUCT_LIST.name){
                ProductScreen()
            }
            composable(Router.ORDER_LIST.name){
                OrderScreen()
            }
            composable(Router.PROFILE.name){
                ProfileScreen()
            }
        }


        composable(Router.RESTAURANT_DETAIL.name){
            RestaurantDetailScreen()
        }
        composable(Router.LOGIN.name){
            val viewModel = hiltViewModel<UserViewModel>()
            LoginScreen(viewModel)
        }
    }
}
