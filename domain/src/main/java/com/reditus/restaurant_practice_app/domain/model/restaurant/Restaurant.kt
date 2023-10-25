package com.reditus.restaurant_practice_app.domain.model.restaurant

import com.reditus.restaurant_practice_app.domain.model.common.ModelWithId

open class Restaurant(
    override val id: String,
    val name: String,
    val thumbUrl: String,
    val tags: List<String>,
    val priceRange: String,
    val ratings: Float,
    val ratingsCount: Int,
    val deliveryTime: Int,
    val deliveryFee: Int,
) : ModelWithId
