package com.example.lostandfound.entity

import java.io.Serializable

class ShortNotification(not: NotificationEntity) {
    var id: Long? = not.id
    var title: String? = not.title
    var subject: String? = not.subject
}

