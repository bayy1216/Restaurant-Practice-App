package com.reditus.restaurant_practice_app.domain.model.restaurant

open class Restaurant(
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
