package com.reditus.restaurant_practice_app.domain.repository.restaurant

import com.reditus.restaurant_practice_app.domain.model.restaurant.Rating
import com.reditus.restaurant_practice_app.domain.repository.common.PaginationRepository

interface RestaurantRatingRepository: PaginationRepository<Rating> {
    suspend fun createRating(
        restaurantId: String,
        rating: Int,
        content: String,
        imgNames: List<String>,
    ): Rating
}