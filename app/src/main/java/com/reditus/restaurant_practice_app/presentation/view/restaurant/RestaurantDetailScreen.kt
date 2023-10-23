package com.reditus.restaurant_practice_app.presentation.view.restaurant

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.reditus.restaurant_practice_app.presentation.common.layout.DefaultLayout
import com.reditus.restaurant_practice_app.presentation.common.navigation.Router

@Composable
fun RestaurantDetailScreen(){
    DefaultLayout(title = Router.RESTAURANT_DETAIL.korean){
        Text(text = Router.RESTAURANT_DETAIL.korean)
    }
}