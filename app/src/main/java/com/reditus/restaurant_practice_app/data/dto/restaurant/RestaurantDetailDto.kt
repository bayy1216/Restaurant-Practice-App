package com.reditus.restaurant_practice_app.data.dto.restaurant

data class RestaurantDetailDto(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val tags: List<String>,
    val priceRange: String,
    val ratings: Float,
    val ratingsCount: Int,
    val deliveryTime: Int,
    val deliveryFee: Int,
    val detail: String,
    val products: List<ProductDto>,
)


data class ProductDto(
    val id: String,
    val name: String,
    val imgUrl: String,
    val detail: String,
    val price: Int,
)