package com.reditus.restaurant_practice_app.presentation.view.restaurant

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.reditus.restaurant_practice_app.presentation.common.layout.DefaultLayout
import com.reditus.restaurant_practice_app.presentation.common.navigation.Router
import com.reditus.restaurant_practice_app.presentation.viewmodel.restaurant.RestaurantViewModel


@Composable
fun RestaurantScreen(
        navController: NavHostController,
        restaurantViewModel: RestaurantViewModel,
    ){
    val restaurants by restaurantViewModel.restaurants.collectAsState()

    DefaultLayout(title = Router.RESTAURANT_LIST.korean){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(text = "Restaurant Screen")
            restaurants.forEach { restaurant ->
                Text(
                    text = restaurant.name,
                    modifier = Modifier.clickable {
                        navController.navigate(Router.RESTAURANT_DETAIL.name)
                    }
                )
            }
        }

    }
}