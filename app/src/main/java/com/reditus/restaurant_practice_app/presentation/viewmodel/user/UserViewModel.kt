package com.reditus.restaurant_practice_app.presentation.viewmodel.user

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reditus.restaurant_practice_app.domain.model.restaurant.Restaurant
import com.reditus.restaurant_practice_app.domain.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val prefs: SharedPreferences
): ViewModel(){

    private val _isLogin = MutableStateFlow(false)
    val isLogin :StateFlow<Boolean> = _isLogin.asStateFlow()

    init {
        prefs.getString("accessToken", null)?.let {
            _isLogin.value = true
        }
    }
    fun login(
        username: String,
        password: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val jwtToken = userRepository.login(username, password)
                Log.d("user", jwtToken.toString())
                prefs.edit().putString("accessToken", jwtToken.accessToken).apply()
                prefs.edit().putString("refreshToken", jwtToken.refreshToken).apply()
                _isLogin.value = true
            }catch (e: Exception){
                Log.d("user", e.toString())
            }
        }
    }
}