package com.example.lostandfound.net.retrofit.model

import com.example.lostandfound.entity.Notification
import com.google.gson.annotations.SerializedName

class NotificationsResponse {
    @SerializedName("notifications")
    val notifications: List<Notification>? = null
}