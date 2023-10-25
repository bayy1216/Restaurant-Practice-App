package com.reditus.restaurant_practice_app.domain.repository.restaurant

import com.reditus.restaurant_practice_app.domain.model.restaurant.Restaurant
import com.reditus.restaurant_practice_app.domain.model.restaurant.RestaurantDetail
import com.reditus.restaurant_practice_app.domain.repository.common.PaginationRepository

interface RestaurantRepository : PaginationRepository<Restaurant> {
    suspend fun getRestaurantDetail(
        id: String,
    ) : RestaurantDetail
}