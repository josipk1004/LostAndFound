package com.example.lostandfound

import android.content.Intent
import android.util.Log
import com.example.lostandfound.entity.Data
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("onMessageReceived", "Message received: ${remoteMessage.from}")

        val broadCastIntent = Intent().apply {
            action = MainActivity.ACTION_RESPONSE
            addCategory(Intent.CATEGORY_DEFAULT)
        }
        broadCastIntent.putExtra("message", remoteMessage.data?.get("message"))
        sendBroadcast(broadCastIntent)
    }

    override fun onNewToken(s: String?) {
        super.onNewToken(s)
        Data.service.setToken(Data.loggedUser!!.username, s)
        Log.e("onNewToken", s!!)
    }

}