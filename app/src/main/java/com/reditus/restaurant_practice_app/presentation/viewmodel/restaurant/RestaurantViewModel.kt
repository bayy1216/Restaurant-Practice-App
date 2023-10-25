package com.reditus.restaurant_practice_app.presentation.viewmodel.restaurant

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.reditus.restaurant_practice_app.domain.model.common.ModelWithId
import com.reditus.restaurant_practice_app.domain.model.restaurant.Restaurant
import com.reditus.restaurant_practice_app.domain.repository.common.PaginationRepository
import com.reditus.restaurant_practice_app.domain.repository.restaurant.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {
    private val _restaurants = MutableStateFlow<PagingData<Restaurant>>(PagingData.empty())
    val restaurants : StateFlow<PagingData<Restaurant>> = _restaurants.asStateFlow()


    init {
        getRestaurants()
    }
    fun getRestaurants(){
        viewModelScope.launch {
            kotlin.runCatching {
                restaurantRepository.paginate()
            }.onSuccess {
                val pagingSourceFactory = { CursorPagingSource<Restaurant>(repository = restaurantRepository) }
                val data = Pager(
                    config = PagingConfig(
                        pageSize = 20,
                        enablePlaceholders = false,
                        maxSize = 100
                    ),
                    pagingSourceFactory = pagingSourceFactory
                ).flow.cachedIn(viewModelScope).collect{
                    _restaurants.value = it
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

}


class CursorPagingSource<T : ModelWithId>(
    private val count: Int = 20,
    private val repository : PaginationRepository<T>
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
                count = count
            )
            LoadResult.Page(
                data = response.data,
                prevKey = null,
                nextKey = if(response.meta.hasMore){
                    response.data.last().id
                }else{
                    null
                }
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}