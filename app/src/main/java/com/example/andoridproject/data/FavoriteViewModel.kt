package com.example.andoridproject.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    val readFavoriteData: LiveData<List<Favorite>>
    private val repository: FavoriteRepository

    init {
        val favoriteDao = UserDatabase.getDatabase(application).favoriteDao()
        repository = FavoriteRepository(favoriteDao)
        readFavoriteData = repository.readFavoriteData
    }

    fun addToFavorites(favorite: Favorite){
        viewModelScope.launch(Dispatchers.IO){
            repository.addToFavorites(favorite)
        }
    }
}