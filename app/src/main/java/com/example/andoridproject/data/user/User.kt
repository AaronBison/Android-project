package com.example.andoridproject.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val address: String,
    val phone_number: String,
    val email: String
//    val listOfFavourites: ArrayList<>
)