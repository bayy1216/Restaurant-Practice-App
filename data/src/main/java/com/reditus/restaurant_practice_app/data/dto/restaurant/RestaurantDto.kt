package com.reditus.restaurant_practice_app.data.dto.restaurant

import com.reditus.restaurant_practice_app.domain.model.restaurant.Restaurant

data class RestaurantDto(
    val id: String,
    val name: String,
    val thumbUrl: String,
    val tags: List<String>,
    val priceRange: String,
    val ratings: Float,
    val ratingsCount: Int,
    val deliveryTime: Int,
    val deliveryFee: Int,
)

fun RestaurantDto.toDomain(): Restaurant{
    return Restaurant(
        id = id,
        name = name,
        thumbUrl = thumbUrl,
        tags = tags,
        priceRange = priceRange,
        ratings = ratings,
        ratingsCount = ratingsCount,
        deliveryTime = deliveryTime,
        deliveryFee = deliveryFee,
    )
}