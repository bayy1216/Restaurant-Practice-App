package com.reditus.restaurant_practice_app.domain.repository.common

import androidx.paging.PagingData
import com.reditus.restaurant_practice_app.domain.model.common.CursorPagination
import com.reditus.restaurant_practice_app.domain.model.common.ModelWithId
import kotlinx.coroutines.flow.Flow

interface PaginationRepository<T : ModelWithId> {
    suspend fun paginate(
        id: String? = null,
        after: String? = null,
        count: Int,
    ) : CursorPagination<T>

    fun getPagingData(count: Int = 20, id:String? = null) : Flow<PagingData<T>>
}