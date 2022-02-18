package com.example.lostandfound.net.retrofit.model

import com.example.lostandfound.entity.Notification
import com.google.gson.annotations.SerializedName

class RegisterResponse {

    @SerializedName("id")
    val id: Long? = null
    @SerializedName("firstName")
    val firstName: String? = null
    @SerializedName("lastName")
    val lastName: String? = null
    @SerializedName("email")
    val email: String? = null
    @SerializedName("username")
    val username: String? = null
    @SerializedName("password")
    val password: String? = null
    @SerializedName("notifications")
    val notifications: List<Notification>? = null
}