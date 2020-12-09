package com.example.andoridproject

class MainScreenItemList(val restaurants: List<MainScreenItem>)
data class MainScreenItem(val id: Int, val name: String, val address: String, val price: String, val image_url: String)