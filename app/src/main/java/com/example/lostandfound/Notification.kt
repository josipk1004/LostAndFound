package com.example.lostandfound

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "Notification")
data class Notification(
    @PrimaryKey
    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String,
    @TypeConverters(DateConverter::class)
    @ColumnInfo(name = "date")
    @SerializedName("date")
//    var date: Date, TODO
    var date: Long,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String,
    @ColumnInfo(name = "adress")
    @SerializedName("adress")
    var adress: String,
    @TypeConverters(UserConverter::class)
    @ColumnInfo(name = "user")
    @SerializedName("user")
//    var user: User TODO
    var user: String
)
  