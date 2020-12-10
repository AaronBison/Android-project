package com.example.andoridproject.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.andoridproject.MainScreenItem

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(favorite: MainScreenItem)

    @Query("SELECT * FROM favorite_table2 ORDER BY id ASC")
    fun readFavoriteData(): LiveData<List<MainScreenItem>>
}