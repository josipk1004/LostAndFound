package com.example.lostandfound.entity

import com.example.lostandfound.net.retrofit.model.RegisterResponse
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class Notification {
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
  