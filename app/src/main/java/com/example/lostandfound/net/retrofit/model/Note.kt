package com.example.lostandfound.net.retrofit.model

import com.google.gson.annotations.SerializedName

class Note(subject: String?, content: String?, data: Map<String, String>?) {

    @SerializedName("subject")
    private val subject: String? = subject

    @SerializedName("content")
    private val content: String? = content

    @SerializedName("data")
    private val data: Map<String, String>? = data

}