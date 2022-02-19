package com.example.lostandfound.net.retrofit.model

import com.google.gson.annotations.SerializedName

class NotificationResponse {
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("subject")
    var subject: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("address")
    var address: String? = null
    @SerializedName("date")
    var date: String? = null
    @SerializedName("user")
    var user: RegisterResponse? = null
}