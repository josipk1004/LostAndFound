package com.example.lostandfound.net.retrofit.model

import com.google.gson.annotations.SerializedName

class LoginRequest(username: String, password: String) {
    @SerializedName("username")
    val username: String? = username
    @SerializedName("password")
    val password: String? = password
}