package com.reditus.restaurant_practice_app.domain.model.restaurant

class RestaurantDetail(
    id: String,
    name: String,
    thumbUrl: String,
    tags: List<String>,
    priceRange: String,
    ratings: Float,
    ratingsCount: Int,
    deliveryTime: Int,
    deliveryFee: Int,
    val detail: String,
    val products: List<Product>,
) : Restaurant(
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
