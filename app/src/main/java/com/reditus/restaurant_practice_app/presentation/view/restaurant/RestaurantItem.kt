package com.reditus.restaurant_practice_app.presentation.view.restaurant

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.reditus.restaurant_practice_app.domain.model.restaurant.Restaurant

object RestaurantItem

@Composable
fun RestaurantItem.fromModel(model: Restaurant){
    RestaurantItemBase(
        name = model.name,
        thumbUrl = model.thumbUrl,
        tags = model.tags,
        priceRange = model.priceRange,
        ratings = model.ratings,
        ratingsCount = model.ratingsCount,
        deliveryTime = model.deliveryTime,
        deliveryFee = model.deliveryFee,
    )
}

@Composable
fun RestaurantItemBase(
    name: String,
    thumbUrl: String,
    tags: List<String>,
    priceRange: String,
    ratings: Float,
    ratingsCount: Int,
    deliveryTime: Int,
    deliveryFee: Int,
){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        AsyncImage(
            model = "https://restaurant-app.run.goorm.site${thumbUrl}",
            contentDescription = null,
        )
        Text(text = name)
        Row {
            Text(text = priceRange)
            Text(text = ratings.toString())
            Text(text = ratingsCount.toString())
            Text(text = deliveryTime.toString())
            Text(text = deliveryFee.toString())
        }
    }
}