package com.example.andoridproject.data.favorite

import androidx.lifecycle.LiveData

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    val readFavoriteData: LiveData<List<MainScreenItem>> = favoriteDao.readFavoriteData()

    suspend fun addToFavorites(favorite: MainScreenItem){
        favoriteDao.addToFavorites(favorite)
    }

    suspend fun updateFavorite(favorite: MainScreenItem){
        favoriteDao.updateFavorite(favorite)
    }
}