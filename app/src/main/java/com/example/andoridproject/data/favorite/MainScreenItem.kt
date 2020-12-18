package com.example.andoridproject.data.favorite

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorite_table2")
data class MainScreenItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val address: String,
    val price: String,
    val image_url: String,
    val favorite: Int,
    val lat: String,
    val lng: String
): Parcelable