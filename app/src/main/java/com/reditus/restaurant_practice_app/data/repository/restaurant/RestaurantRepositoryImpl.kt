package com.reditus.restaurant_practice_app.data.repository.restaurant

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.reditus.restaurant_practice_app.data.api.ApiService
import com.reditus.restaurant_practice_app.data.dto.common.toDomain
import com.reditus.restaurant_practice_app.data.dto.restaurant.toDomain
import com.reditus.restaurant_practice_app.domain.model.common.CursorPagination
import com.reditus.restaurant_practice_app.domain.model.common.CursorPagingSource
import com.reditus.restaurant_practice_app.domain.model.restaurant.Restaurant
import com.reditus.restaurant_practice_app.domain.model.restaurant.RestaurantDetail
import com.reditus.restaurant_practice_app.domain.repository.restaurant.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : RestaurantRepository {
    override suspend fun getRestaurantDetail(id: String): RestaurantDetail {
        val resp = apiService.getRestaurant(id)
        return resp.toDomain()
    }

    override suspend fun paginate(
        id: String?,
        after: String?,
        count: Int
    ): CursorPagination<Restaurant> {
        val resp = apiService.paginateRestaurant(after, count)
        val domain = CursorPagination<Restaurant>(
            meta = resp.meta.toDomain(),
            data = resp.data.map {
                it.toDomain()
            }
        )
        return domain
    }

    override fun getPagingData(count: Int, id: String?): Flow<PagingData<Restaurant>> {
        val pagingSourceFactory = { CursorPagingSource(count,null,this,) }
        return Pager(
            config = PagingConfig(
                pageSize = count,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}