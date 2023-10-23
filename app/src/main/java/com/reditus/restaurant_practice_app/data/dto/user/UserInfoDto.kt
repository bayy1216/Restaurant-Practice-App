package com.reditus.restaurant_practice_app.data.dto.user

import com.reditus.restaurant_practice_app.domain.model.user.UserInfo

data class UserInfoDto(
    val id: String,
    val username: String,
    val imageUrl: String,
)

fun UserInfoDto.toDomain() : UserInfo{
    return UserInfo(
        id = this.id,
        username = this.username,
        imageUrl = this.imageUrl,
    )
}