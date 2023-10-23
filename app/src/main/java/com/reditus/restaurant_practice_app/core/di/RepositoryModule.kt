package com.reditus.restaurant_practice_app.core.di

import com.reditus.restaurant_practice_app.data.repository.restaurant.RestaurantRatingRepositoryImpl
import com.reditus.restaurant_practice_app.data.repository.restaurant.RestaurantRepositoryImpl
import com.reditus.restaurant_practice_app.data.repository.user.UserRepositoryImpl
import com.reditus.restaurant_practice_app.domain.repository.restaurant.RestaurantRatingRepository
import com.reditus.restaurant_practice_app.domain.repository.restaurant.RestaurantRepository
import com.reditus.restaurant_practice_app.domain.repository.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Singleton
    @Binds
    abstract fun provideRestaurantRepository(
        restaurantRepositoryImpl: RestaurantRepositoryImpl
    ): RestaurantRepository

    @Singleton
    @Binds
    abstract fun provideRestaurantRatingRepository(
        restaurantRatingRepositoryImpl: RestaurantRatingRepositoryImpl
    ): RestaurantRatingRepository
}