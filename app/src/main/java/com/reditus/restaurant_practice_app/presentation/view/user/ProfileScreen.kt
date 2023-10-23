package com.reditus.restaurant_practice_app.presentation.view.user

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.reditus.restaurant_practice_app.presentation.common.layout.DefaultLayout
import com.reditus.restaurant_practice_app.presentation.common.navigation.Router

@Composable
fun ProfileScreen(){
    DefaultLayout(title = Router.PROFILE.korean){
        Text(text = Router.PROFILE.korean)
    }
}