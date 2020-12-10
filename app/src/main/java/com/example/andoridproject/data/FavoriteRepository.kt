package com.example.andoridproject.data

import androidx.lifecycle.LiveData

class FavoriteRepository(private val restaurantDao: FavoriteDao) {

    val readFavoriteData: LiveData<List<Favorite>> = restaurantDao.readAllData()

    suspend fun addToFavorites(favorite: Favorite){
        restaurantDao.addToFavorites(favorite)
    }
}