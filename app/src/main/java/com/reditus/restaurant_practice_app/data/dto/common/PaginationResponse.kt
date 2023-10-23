package com.reditus.restaurant_practice_app.data.dto.common

data class PaginationResponse<T>(
    val meta: MetaDto,
    val data: List<T>,
)


data class MetaDto(
    val count: Int,
    val hasMore: Boolean,
)