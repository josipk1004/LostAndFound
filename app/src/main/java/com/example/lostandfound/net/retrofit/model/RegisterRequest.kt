package com.example.lostandfound.net.retrofit.model

import com.google.gson.annotations.SerializedName

class RegisterRequest(firstName: String, lastName: String?, email: String, username: String,
                      password: String){
    @SerializedName("firstName")
    var firstName: String? = firstName
    @SerializedName("lastName")
    var lastName: String? = lastName
    @SerializedName("email")
    var email: String? = email
    @SerializedName("username")
    var username: String? = username
    @SerializedName("password")
    var password: String? = password


}