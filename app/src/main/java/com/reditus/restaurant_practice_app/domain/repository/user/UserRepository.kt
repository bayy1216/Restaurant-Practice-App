package com.reditus.restaurant_practice_app.domain.repository.user

import com.reditus.restaurant_practice_app.domain.model.user.JwtToken

interface UserRepository {
    suspend fun login(
        username: String,
        password: String
    ) : JwtToken
}