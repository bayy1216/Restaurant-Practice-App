package com.reditus.restaurant_practice_app.presentation.view.product

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.reditus.restaurant_practice_app.presentation.common.layout.DefaultLayout
import com.reditus.restaurant_practice_app.presentation.common.navigation.Router


@Composable
fun ProductScreen(){
    DefaultLayout(title = Router.PRODUCT_LIST.korean){
        Text(text = Router.PRODUCT_LIST.korean)
    }
}