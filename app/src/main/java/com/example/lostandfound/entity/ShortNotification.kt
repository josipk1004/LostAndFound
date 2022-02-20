package com.example.lostandfound.entity

import java.io.Serializable

data class ShortNotification(
    var id: Long? = null,
    var title: String = "",
    var subject: String = ""
) : Serializable {
    constructor(c: Notification) : this(
        c.id,
        c.title!!,
        c.subject!!
    )

    override fun toString(): String {
        return title + "\n" + subject
    }
}
