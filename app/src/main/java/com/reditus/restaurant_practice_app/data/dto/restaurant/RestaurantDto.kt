package com.reditus.restaurant_practice_app.data.dto.restaurant

data class RestaurantDto(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val tags: List<String>,
    val priceRange: String,
    val ratings: Float,
    val ratingsCount: Int,
    val deliveryTime: Int,
    val deliveryFee: Int,
)