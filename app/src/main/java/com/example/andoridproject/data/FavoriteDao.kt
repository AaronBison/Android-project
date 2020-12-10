package com.example.andoridproject.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(favorite: Favorite)

    @Query("SELECT * FROM favorite_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Favorite>>
}