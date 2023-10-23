package com.reditus.restaurant_practice_app.data.api

import com.reditus.restaurant_practice_app.data.dto.common.PaginationResponse
import com.reditus.restaurant_practice_app.data.dto.restaurant.CreateRatingRequest
import com.reditus.restaurant_practice_app.data.dto.restaurant.RatingDto
import com.reditus.restaurant_practice_app.data.dto.restaurant.RestaurantDetailDto
import com.reditus.restaurant_practice_app.data.dto.restaurant.RestaurantDto
import com.reditus.restaurant_practice_app.data.dto.user.LoginResponse
import com.reditus.restaurant_practice_app.data.dto.user.UserInfoDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("/auth/login")
    @Headers("Auth: false")
    suspend fun login(
        @Header("authorization") authorization: String
    ): LoginResponse

    @POST("/user/me")
    suspend fun getMyInfo(): UserInfoDto

    ////////////////////////////

    @GET("/restaurant")
    suspend fun paginateRestaurant(
        @Query("after") after: String?,
        @Query("count") count: Int = 20,
    ) : PaginationResponse<RestaurantDto>

    @GET("/restaurant/{rid}")
    suspend fun getRestaurant(
        @Path("rid") rid: String,
    ) : RestaurantDetailDto

    @GET("/restaurant/{rid}/rating")
    suspend fun paginateRating(
        @Path("rid") rid: String,
        @Query("after") after: String?,
        @Query("count") count: Int = 20,
    ) : PaginationResponse<RatingDto>

    @POST("/restaurant/{rid}/rating")
    suspend fun createRating(
        @Path("rid") rid: String,
        @Body createRatingRequest: CreateRatingRequest
    ) : RatingDto
}