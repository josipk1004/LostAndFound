package com.example.lostandfound.entity

import java.io.Serializable

data class User(
    var id: Long? = null,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val notifications: List<Notification>? = null
    ) : Serializable
