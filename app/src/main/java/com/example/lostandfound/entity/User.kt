package com.example.lostandfound.entity

import java.io.Serializable

data class User(
    var id: Long? = null,
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var username: String = "",
    var password: String = "",
    var notifications: MutableList<Notification>? = null
    ) : Serializable
