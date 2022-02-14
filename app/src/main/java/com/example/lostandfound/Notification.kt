package com.example.lostandfound

import androidx.room.*
import androidx.room.Relation
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "Notification")
data class Notification(
    @PrimaryKey(autoGenerate = true)
    var notificationId : Int,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String,
    @TypeConverters(DateConverter::class)
    @ColumnInfo(name = "date")
    @SerializedName("date")
//    var date: Date
//    , TODO
    var date: Long,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String,
    @ColumnInfo(name = "adress")
    @SerializedName("adress")
    var adress: String,
    @Embedded
//    @Relation(parentColumn = "userId", entityColumn = "userNotificationId")
    var user: User,
//    var userNotificationId: Int
//    var user: String
// TODO
)
  