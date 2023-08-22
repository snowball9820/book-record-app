package com.app.bookrecordapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Query("SELECT * FROM user WHERE text IS NOT NULL AND text != ''")
    fun getAll(): List<User>

    @Query("SELECT * FROM user")
    fun getAll2(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT textId FROM user")
    fun getAllTextIds(): List<String>

    @Query("SELECT uid, title, description, selectedImageUri FROM user WHERE title IS NOT NULL AND title != '' AND description IS NOT NULL AND description != ''")
    fun getAllTitlesDescriptionsAndImageUris(): Flow<List<TitleDescriptionImage>>

    data class TitleDescriptionImage(
        val uid:String,
        val title: String,
        val description: String,
        val selectedImageUri: String?
    )


    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

}