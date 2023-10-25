package com.reditus.restaurant_practice_app.data.dto.common

import com.reditus.restaurant_practice_app.domain.model.common.Meta

data class PaginationResponse<T>(
    val meta: MetaDto,
    val data: List<T>,
)


data class MetaDto(
    val count: Int,
    val hasMore: Boolean,
)

fun MetaDto.toDomain(): Meta {
    return Meta(
        count = count,
        hasMore = hasMore,
    )
}