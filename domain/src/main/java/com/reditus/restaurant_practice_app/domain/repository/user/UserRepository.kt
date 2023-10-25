package com.reditus.restaurant_practice_app.domain.repository.user

import com.reditus.restaurant_practice_app.domain.model.user.JwtToken
import com.reditus.restaurant_practice_app.domain.model.user.UserInfo

interface UserRepository {
    suspend fun login(
        username: String,
        password: String
    ) : JwtToken

    suspend fun getMyInfo() : UserInfo
}