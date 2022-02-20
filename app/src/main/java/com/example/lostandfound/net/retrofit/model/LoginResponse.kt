package com.example.lostandfound.net.retrofit.model

import com.example.lostandfound.entity.Notification
import com.google.gson.annotations.SerializedName

class LoginResponse {
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
    val notifications: List<Notification>? = null
}