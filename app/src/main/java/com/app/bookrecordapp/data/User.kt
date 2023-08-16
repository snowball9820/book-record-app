package com.app.bookrecordapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo( "textId") val textId: String,
    @ColumnInfo("textPw") val textPw: String? = null,
)
