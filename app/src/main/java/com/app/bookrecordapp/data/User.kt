package com.app.bookrecordapp.data

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.app.bookrecordapp.UriTypeConverter

@Entity
@TypeConverters(UriTypeConverter::class)
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo( "textId") val textId: String,
    @ColumnInfo("textPw") val textPw: String? = null,
    @ColumnInfo("text") val text:String,
    @ColumnInfo("title") val title:String,
    @ColumnInfo("description") val description:String,
    @ColumnInfo(name = "selectedImageUri") val selectedImageUri: Uri? = null

)
