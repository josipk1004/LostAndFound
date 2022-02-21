package com.example.lostandfound.net.retrofit.model

import com.example.lostandfound.entity.User
import com.google.gson.annotations.SerializedName

class NotificationRequest(title: String?, subject: String?, description: String?,
                            address: String?, date: String?) {
    @SerializedName("title")
    var title: String? = title
    @SerializedName("subject")
    var subject: String? = subject
    @SerializedName("description")
    var description: String? = description
    @SerializedName("address")
    var address: String? = address
    @SerializedName("date")
    var date: String? = date
}