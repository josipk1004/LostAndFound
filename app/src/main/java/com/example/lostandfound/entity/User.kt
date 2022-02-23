package com.example.lostandfound.entity

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("id")
    val id: Long? = null

    @SerializedName("firstName")
    val firstName: String = ""

    @SerializedName("lastName")
    val lastName: String = ""

    @SerializedName("email")
    val email: String = ""

    @SerializedName("username")
    val username: String = ""

    @SerializedName("password")
    val password: String = ""

    @SerializedName("notifications")
    val notifications: List<NotificationEntity>? = null

    @SerializedName("token")
    var token: String? = null

}

