package com.reditus.restaurant_practice_app.presentation.viewmodel.user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reditus.restaurant_practice_app.domain.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel(){


    private fun login(
        username: String,
        password: String
    ) {
        viewModelScope.launch {
            val jwtToken = userRepository.login(username, password)
            //TODO
            Log.d("user", "login: $jwtToken")
        }
    }
}