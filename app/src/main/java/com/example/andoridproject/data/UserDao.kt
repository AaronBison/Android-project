package com.example.andoridproject.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import okhttp3.internal.ignoreIoExceptions

// This contains all the methods used for accessing the database, quarries, etc..

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
}