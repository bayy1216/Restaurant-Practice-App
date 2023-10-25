package com.reditus.restaurant_practice_app.domain.model.common

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.reditus.restaurant_practice_app.domain.repository.common.PaginationRepository

class CursorPagingSource<T : ModelWithId>(
    private val count: Int,
    private val id: String?,
    private val repository: PaginationRepository<T>
) : PagingSource<String, T>() {
    override fun getRefreshKey(state: PagingState<String, T>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, T> {
        return try {
            val response = repository.paginate(
                after = params.key,
                count = count,
                id = id
            )
            LoadResult.Page(
                data = response.data,
                prevKey = params.key,
                nextKey = if (response.meta.hasMore) {
                    response.data.last().id
                } else {
                    null
                }
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}