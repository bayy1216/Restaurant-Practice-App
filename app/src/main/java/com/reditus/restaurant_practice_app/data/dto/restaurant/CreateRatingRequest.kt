package com.reditus.restaurant_practice_app.data.dto.restaurant

data class CreateRatingRequest(
    val restaurantId: String,
    val rating: Int,
    val content: String,
    val imageNames: List<String>,
)