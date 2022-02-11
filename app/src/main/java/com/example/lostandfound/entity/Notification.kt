package com.example.lostandfound.entity

import java.io.Serializable
import java.util.*

data class Notification(
    var id: Long? = null,
    var title: String = "",
    var subject: String = "",
    var date: Date? = null,
    var description: String = "",
    var address: String = "",
    var user : User? = null
) : Serializable
  