package com.example.lostandfound

import com.example.lostandfound.entity.User
import java.util.*

data class Notification(var title: String, var date: Date, var description: String,
                            var adress: String, var user : User
)
  