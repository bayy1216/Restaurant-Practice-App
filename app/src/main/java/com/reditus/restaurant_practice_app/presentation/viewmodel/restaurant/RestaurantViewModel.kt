package com.reditus.restaurant_practice_app.presentation.viewmodel.restaurant

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.reditus.restaurant_practice_app.domain.model.common.ModelWithId
import com.reditus.restaurant_practice_app.domain.model.restaurant.Restaurant
import com.reditus.restaurant_practice_app.domain.repository.common.PaginationRepository
import com.reditus.restaurant_practice_app.domain.repository.restaurant.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {
    private val _restaurants = MutableStateFlow<PagingData<Restaurant>>(PagingData.empty())
    val restaurants: StateFlow<PagingData<Restaurant>> = _restaurants.asStateFlow()


    init {
        getRestaurants()
    }

    private fun getRestaurants() {
        viewModelScope.launch {
            kotlin.runCatching {
                restaurantRepository.getPagingData()
            }.onSuccess {
                it.cachedIn(viewModelScope)
                    .collect {
                        _restaurants.value = it
                    }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

}

