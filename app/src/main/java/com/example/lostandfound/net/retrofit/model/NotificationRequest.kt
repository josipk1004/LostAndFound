package com.example.lostandfound.net.retrofit.model

import com.example.lostandfound.entity.User
import com.google.gson.annotations.SerializedName

class NotificationRequest(title: String?, subject: String?, description: String?,
                            address: String?, date: String?) {
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
}