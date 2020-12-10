package com.example.andoridproject.data.favorite

import androidx.lifecycle.LiveData

class FavoriteRepository(private val restaurantDao: FavoriteDao) {

    val readFavoriteData: LiveData<List<MainScreenItem>> = restaurantDao.readFavoriteData()

    suspend fun addToFavorites(favorite: MainScreenItem){
        restaurantDao.addToFavorites(favorite)
    }
}