package com.example.lostandfound

sealed class NotificationListItem(val title: String, val user: String) {
    data class Item(val notification: Notification) : NotificationListItem(notification.title, notification.user)
    data class Separator(private val letter: Char) : NotificationListItem(letter.toUpperCase().toString(), "")

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
