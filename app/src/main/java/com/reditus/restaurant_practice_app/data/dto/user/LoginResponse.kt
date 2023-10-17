package com.reditus.restaurant_practice_app.data.dto.user

import com.reditus.restaurant_practice_app.domain.model.user.JwtToken

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
)

fun LoginResponse.toDomain(): JwtToken {
    return JwtToken(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken
    )
}