package com.reditus.restaurant_practice_app.data.dto.restaurant

import com.reditus.restaurant_practice_app.data.dto.user.UserInfoDto
import com.reditus.restaurant_practice_app.data.dto.user.toDomain
import com.reditus.restaurant_practice_app.domain.model.restaurant.Rating

data class RatingDto(
    val id: String,
    val user: UserInfoDto,
    val rating: Int,
    val content: String,
    val imgUrls: List<String>,
)


fun RatingDto.toDomain(): Rating{
    return Rating(
        id = id,
        user = user.toDomain(),
        rating = rating,
        content = content,
        imgUrls = imgUrls,
    )
}