package com.example.andoridproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.andoridproject.data.favorite.MainScreenItem
import com.example.andoridproject.data.favorite.FavoriteDao
import com.example.andoridproject.data.user.User
import com.example.andoridproject.data.user.UserDao

@Database(entities = [User::class, MainScreenItem::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun favoriteDao(): FavoriteDao

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        // This instantiates a database, if there is an instance already existing, it just returns that same instance and doesn't create a new one
        fun getDatabase(context: Context): UserDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "my_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}