package com.reditus.restaurant_practice_app.presentation.viewmodel.restaurant

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reditus.restaurant_practice_app.domain.model.restaurant.Restaurant
import com.reditus.restaurant_practice_app.domain.repository.restaurant.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {
    private val _restaurants = MutableStateFlow(listOf<Restaurant>())
    val restaurants : StateFlow<List<Restaurant>> = _restaurants.asStateFlow()


    init {
        getRestaurants()
    }
    fun getRestaurants(){
        viewModelScope.launch {
            try{
                val resp = restaurantRepository.paginate()
                _restaurants.value = resp.data
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

}