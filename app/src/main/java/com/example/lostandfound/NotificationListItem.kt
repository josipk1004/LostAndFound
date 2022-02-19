package com.example.lostandfound

import com.example.lostandfound.entity.ShortNotification

sealed class NotificationListItem(val title: String, val subject: String) {
    data class Item(val notification: ShortNotification) : NotificationListItem(notification.title, notification.subject)
    data class Separator(private val letter: Char) : NotificationListItem(letter.uppercaseChar().toString(), "")

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
