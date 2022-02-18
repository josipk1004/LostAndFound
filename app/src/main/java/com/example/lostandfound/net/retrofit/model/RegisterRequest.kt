package com.example.lostandfound.net.retrofit.model

import com.google.gson.annotations.SerializedName

class RegisterRequest(firstName: String, lastName: String?, email: String, username: String,
                      password: String){
    @SerializedName("firstName")
    val firstName: String? = firstName
    @SerializedName("lastName")
    val lastName: String? = lastName
    @SerializedName("email")
    val email: String? = email
    @SerializedName("username")
    val username: String? = username
    @SerializedName("password")
    val password: String? = password


}