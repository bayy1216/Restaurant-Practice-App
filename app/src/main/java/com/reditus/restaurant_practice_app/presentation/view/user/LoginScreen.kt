package com.reditus.restaurant_practice_app.presentation.view.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.reditus.restaurant_practice_app.presentation.viewmodel.user.UserViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.reditus.restaurant_practice_app.presentation.common.layout.DefaultLayout
import com.reditus.restaurant_practice_app.presentation.common.navigation.Router

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    val id = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val isLogin = viewModel.isLogin.collectAsState()
    DefaultLayout {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text ="이메일과 비밀번호를 입력해주세요!")
            Spacer(modifier = Modifier.height(100.dp))
            TextField(
                value = id.value,
                onValueChange = { id.value = it }
            )

            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = password.value,
                onValueChange = { password.value = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                onClick = {
                    viewModel.login(id.value, password.value)
                },
            ) {
                Text(text = "Login")

            }
            TextButton(onClick = {
                if(isLogin.value){
                    navController.navigate(Router.RESTAURANT_LIST.name)
                }
            }) {
                Text(text = "Sign Up")
            }
        }
    }
}