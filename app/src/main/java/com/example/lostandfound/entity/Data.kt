package com.example.lostandfound.entity

import com.example.lostandfound.net.retrofit.apiClient.ApiClient
import com.example.lostandfound.net.retrofit.model.NotificationRequest

class Data {

    companion object{
        val service = ApiClient.create()
        var loggedUser: User? = null
        var notificationUpdate: NotificationRequest? = null

        fun setUser(u: User){
            loggedUser = u
        }

        fun setNotificationRequest (notif: NotificationRequest){
            notificationUpdate = notif
        }
    }
}