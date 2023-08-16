package com.app.bookrecordapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1) //User 말고도 다른 것도 가능, version도 계속 업됨
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
}
