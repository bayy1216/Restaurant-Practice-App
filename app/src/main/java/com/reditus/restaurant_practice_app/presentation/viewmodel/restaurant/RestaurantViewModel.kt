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

    private val hasMore = MutableStateFlow(true)

    init {
        getRestaurants()
    }
    fun getRestaurants(){
        viewModelScope.launch {
            kotlin.runCatching {
                restaurantRepository.paginate()
            }.onSuccess {
                _restaurants.value = it.data
                hasMore.value = it.meta.hasMore
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

}