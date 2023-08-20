package com.app.bookrecordapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {

    @Query("SELECT * FROM user WHERE text IS NOT NULL AND text != ''")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT textId FROM user")
    fun getAllTextIds(): List<String>


    @Query("SELECT title, description, selectedImageUri FROM user WHERE title IS NOT NULL AND title != '' AND description IS NOT NULL AND description != ''")
    fun getAllTitlesDescriptionsAndImageUris(): List<TitleDescriptionImage>

    data class TitleDescriptionImage(
        val title: String,
        val description: String,
        val selectedImageUri: String? // 이 부분은 User 엔티티에서 Uri 타입을 String으로 변환하였다고 가정
    )




    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

}