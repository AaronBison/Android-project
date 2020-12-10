package com.example.andoridproject.data

import androidx.lifecycle.LiveData
import com.example.andoridproject.MainScreenItem

class FavoriteRepository(private val restaurantDao: FavoriteDao) {

    val readFavoriteData: LiveData<List<MainScreenItem>> = restaurantDao.readFavoriteData()

    suspend fun addToFavorites(favorite: MainScreenItem){
        restaurantDao.addToFavorites(favorite)
    }
}