package com.example.andoridproject.data.favorite

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.andoridproject.data.user.User

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(favorite: MainScreenItem)

    @Update
    suspend fun updateFavorite(favorite: MainScreenItem)

    @Delete
    suspend fun deleteFromFavorites(favorite: MainScreenItem)

    @Query("SELECT * FROM favorite_table2 ORDER BY id ASC")
    fun readFavoriteData(): LiveData<List<MainScreenItem>>
}