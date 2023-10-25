package com.reditus.restaurant_practice_app.domain.model.user

data class JwtToken(
    val accessToken: String,
    val refreshToken: String,
)
