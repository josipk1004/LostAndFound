package com.example.lostandfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lostandfound.entity.Data
import com.example.lostandfound.entity.NotificationEntity
import kotlinx.android.synthetic.main.activity_notification_details.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_details)

        val id = intent.getSerializableExtra("id") as Long

        GlobalScope.launch { setNotification(id) }

        backToAllNotifications.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()

        if(Data.loggedUser == null)
            startActivity(Intent(this, Login::class.java))

        if(Data.loggedUser?.id == null)
            startActivity(Intent(this, Login::class.java))
    }

    suspend fun setNotification(id: Long){
        var notification: NotificationEntity? = null

        val callNotif = (GlobalScope.async {Data.service.getNotification(id)  }).await()

        callNotif.enqueue(object: Callback<NotificationEntity> {
            override fun onResponse(
                call: Call<NotificationEntity>?,
                response: Response<NotificationEntity>
            ) {
                if (response.code() == 200) {
                    notification = response.body()
                    titleNotificationDetail.text = notification?.title
                    ownerNotificationDetail.setText(notification?.username)
                    subjectNotificationDetail.setText(notification?.subject)
                    dateNotificationDetail.setText(notification?.date.toString())
                    addressNotificationDetail.setText(notification?.address)
                    descrNotificationDetail.setText(notification?.description)
                } else {
                    val text = "Something went wrong"
                    val length = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(applicationContext, response.code(), length)
                    toast.show()
                    return
                }
            }
            override fun onFailure(call: Call<NotificationEntity>?, t: Throwable?) {
                Toast.makeText(applicationContext, t?.message, Toast.LENGTH_LONG).show()                    }
        })

    }
}