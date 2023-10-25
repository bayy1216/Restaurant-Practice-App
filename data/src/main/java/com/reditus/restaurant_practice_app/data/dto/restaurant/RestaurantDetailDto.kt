package com.reditus.restaurant_practice_app.data.dto.restaurant

import com.reditus.restaurant_practice_app.domain.model.restaurant.Product
import com.reditus.restaurant_practice_app.domain.model.restaurant.RestaurantDetail

data class RestaurantDetailDto(
    val id: String,
    val name: String,
    val thumbUrl: String,
    val tags: List<String>,
    val priceRange: String,
    val ratings: Float,
    val ratingsCount: Int,
    val deliveryTime: Int,
    val deliveryFee: Int,
    val detail: String,
    val products: List<ProductDto>,
)

fun RestaurantDetailDto.toDomain(): RestaurantDetail{
    return RestaurantDetail(
        id = id,
        name = name,
        thumbUrl = thumbUrl,
        tags = tags,
        priceRange = priceRange,
        ratings = ratings,
        ratingsCount = ratingsCount,
        deliveryTime = deliveryTime,
        deliveryFee = deliveryFee,
        detail = detail,
        products = products.map { it.toDomain() },
    )
}


data class ProductDto(
    val id: String,
    val name: String,
    val imgUrl: String,
    val detail: String,
    val price: Int,
)

fun ProductDto.toDomain():Product{
    return Product(
        id = id,
        name = name,
        imgUrl = imgUrl,
        detail = detail,
        price = price,
    )
}