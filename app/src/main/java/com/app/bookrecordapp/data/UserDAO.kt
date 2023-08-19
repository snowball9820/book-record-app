package com.app.bookrecordapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

//    //SQL Query 문법 찾아 보기
//    @Query("SELECT * FROM user WHERE id LIKE :name LIMIT 1 ")
//    fun findByName(name: String): User

    @Query("SELECT textId FROM user")
    fun getAllTextIds(): List<String>


    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

}