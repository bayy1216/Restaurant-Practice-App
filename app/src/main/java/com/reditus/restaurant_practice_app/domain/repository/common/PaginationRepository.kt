package com.reditus.restaurant_practice_app.domain.repository.common

import com.reditus.restaurant_practice_app.domain.model.common.CursorPagination

interface PaginationRepository<T> {
    suspend fun paginate(
        after: String,
        count: Int = 20,
    ) : CursorPagination<T>
}