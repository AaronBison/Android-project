package com.example.andoridproject

data class MainScreenItem(val id: Int, val name: String, val address: String, val price: String, val image_url: String)
data class MainScreenItemList(val restaurants: List<MainScreenItem>)