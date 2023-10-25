package com.reditus.restaurant_practice_app.domain.model.restaurant

import com.reditus.restaurant_practice_app.domain.model.common.ModelWithId
import com.reditus.restaurant_practice_app.domain.model.user.UserInfo


class Rating (
    override val id: String,
    val user: UserInfo,
    val rating: Int,
    val content: String,
    val imgUrls: List<String>,
) : ModelWithId