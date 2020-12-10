package com.example.andoridproject.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.andoridproject.MainScreenItem
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
        viewModelScope.launch(Dispatchers.IO){
            repository.addToFavorites(favorite)
        }
    }
}