package com.reditus.restaurant_practice_app.domain.model.common

data class CursorPagination<T> (
    val meta: Meta,
    val data: List<T>,
)

data class Meta(
    val count: Int,
    val hasMore: Boolean,
)