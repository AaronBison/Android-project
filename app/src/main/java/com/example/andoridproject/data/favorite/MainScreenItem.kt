package com.example.andoridproject.data.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table2")
data class MainScreenItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val address: String,
    val price: String,
    val image_url: String
)