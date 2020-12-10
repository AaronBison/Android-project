package com.example.andoridproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_table")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val user_id: Int,
    val id: Int,
    val name: String,
    val address: String,
    val price: String,
    val image_url: String
)