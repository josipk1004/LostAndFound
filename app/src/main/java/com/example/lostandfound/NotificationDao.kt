package com.example.lostandfound

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotificationDao {
    @Query("SELECT * FROM Notification ORDER BY title COLLATE NOCASE ASC")
    fun allNotificationsByName() : PagingSource<Int, Notification>

    @Insert
    fun insert(notifications: List<Notification>)

    @Insert
    fun insert(notification: Notification)

    @Delete
    fun delete(notification: Notification)
}