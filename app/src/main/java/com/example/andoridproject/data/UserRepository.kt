package com.example.andoridproject.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readUserData: LiveData<List<User>> = userDao.readUserData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}