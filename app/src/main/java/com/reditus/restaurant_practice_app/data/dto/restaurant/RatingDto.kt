package com.reditus.restaurant_practice_app.data.dto.restaurant

import com.reditus.restaurant_practice_app.data.dto.user.UserInfoDto

data class RatingDto(
    val id: String,
    val user: UserInfoDto,
    val rating: Int,
    val content: String,
    val imgUrls: List<String>,
)
