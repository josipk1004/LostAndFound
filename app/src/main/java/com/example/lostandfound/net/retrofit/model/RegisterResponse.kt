package com.example.lostandfound.net.retrofit.model

import com.example.lostandfound.entity.Notification
import com.google.gson.annotations.SerializedName

class RegisterResponse {

    @SerializedName("id")
    var id: Long? = null
    @SerializedName("firstName")
    var firstName: String? = null
    @SerializedName("lastName")
    var lastName: String? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("username")
    var username: String? = null
    @SerializedName("password")
    var password: String? = null
    @SerializedName("notifications")
    var notifications: List<Notification>? = null
}