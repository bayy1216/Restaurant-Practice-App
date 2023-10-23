package com.reditus.restaurant_practice_app.data.repository.restaurant

import com.reditus.restaurant_practice_app.data.api.ApiService
import com.reditus.restaurant_practice_app.data.dto.common.toDomain
import com.reditus.restaurant_practice_app.data.dto.restaurant.CreateRatingRequest
import com.reditus.restaurant_practice_app.data.dto.restaurant.toDomain
import com.reditus.restaurant_practice_app.domain.model.common.CursorPagination
import com.reditus.restaurant_practice_app.domain.model.restaurant.Rating
import com.reditus.restaurant_practice_app.domain.repository.restaurant.RestaurantRatingRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRatingRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): RestaurantRatingRepository{
    override suspend fun createRating(
        restaurantId: String,
        rating: Int,
        content: String,
        imgNames: List<String>
    ): Rating {
        val body = CreateRatingRequest(restaurantId,rating, content, imgNames)
        val resp = apiService.createRating(restaurantId, body)
        return resp.toDomain()
    }

    override suspend fun paginate(
        id: String?,
        after: String,
        count: Int
    ): CursorPagination<Rating> {
        val resp = apiService.paginateRating(id!!, after, count)
        val domain = CursorPagination<Rating>(
            meta = resp.meta.toDomain(),
            data = resp.data.map {
                it.toDomain()
            }
        )
        return domain
    }
}