package com.example.andoridproject.data.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.andoridproject.data.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    val readFavoriteData: LiveData<List<MainScreenItem>>
    private val repository: FavoriteRepository

    init {
        val favoriteDao = UserDatabase.getDatabase(application).favoriteDao()
        repository = FavoriteRepository(favoriteDao)
        readFavoriteData = repository.readFavoriteData
    }

    fun addToFavorites(favorite: MainScreenItem){
        // This will be ran in a background thread
        viewModelScope.launch(Dispatchers.IO){
            repository.addToFavorites(favorite)
        }
    }

    fun updateFavorite(favorite: MainScreenItem){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateFavorite(favorite)
        }
    }
}