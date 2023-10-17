package com.reditus.restaurant_practice_app.data.api.user

import com.reditus.restaurant_practice_app.data.dto.user.LoginResponse
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {
    @POST("/auth/login")
    suspend fun login(
        @Header("authorization") authorization: String
    ): LoginResponse
}