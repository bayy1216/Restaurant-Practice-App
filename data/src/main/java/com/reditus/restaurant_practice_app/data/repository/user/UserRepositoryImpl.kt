package com.reditus.restaurant_practice_app.data.repository.user

import android.os.Build
import androidx.annotation.RequiresApi
import com.reditus.restaurant_practice_app.data.api.ApiService
import com.reditus.restaurant_practice_app.data.dto.user.toDomain
import com.reditus.restaurant_practice_app.domain.model.user.JwtToken
import com.reditus.restaurant_practice_app.domain.model.user.UserInfo
import com.reditus.restaurant_practice_app.domain.repository.user.UserRepository
import java.nio.charset.StandardCharsets
import java.util.Base64
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun login(username: String, password: String): JwtToken {
        val serialized = plainToBase64("$username:$password")
        val resp = apiService.login("Basic $serialized")
        return resp.toDomain()
    }

    override suspend fun getMyInfo(): UserInfo {
        val resp = apiService.getMyInfo()
        return resp.toDomain()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun plainToBase64(plain: String): String {
        val plainBytes = plain.toByteArray(StandardCharsets.UTF_8)
        val encodedBytes = Base64.getEncoder().encode(plainBytes)
        return String(encodedBytes, StandardCharsets.UTF_8)
    }
}