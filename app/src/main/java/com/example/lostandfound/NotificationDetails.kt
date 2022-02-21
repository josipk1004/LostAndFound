package com.example.lostandfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lostandfound.entity.Data
import com.example.lostandfound.net.retrofit.model.NotificationResponse
import kotlinx.android.synthetic.main.activity_notification_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_details)

        val id = intent.getSerializableExtra("id") as Long
        var notification: NotificationResponse? = null

        val callNotif = Data.service.getNotification(id)

        callNotif.enqueue(object: Callback<NotificationResponse> {
            override fun onResponse(
                call: Call<NotificationResponse>?,
                response: Response<NotificationResponse>
            ) {
                if (response.code() == 200) {
                    notification = response.body()
                } else {
                    val text = "Something went wrong"
                    val length = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(applicationContext, text, length)
                    toast.show()
                    return
                }
            }
            override fun onFailure(call: Call<NotificationResponse>?, t: Throwable?) {
                Toast.makeText(applicationContext, t?.message, Toast.LENGTH_LONG).show()                    }
        })

        titleNotificationDetail.text = notification?.title
        ownerNotificationDetail.text = notification?.user?.username
        subjectNotificationDetail.text = notification?.subject
        dateNotificationDetail.setText(notification?.date.toString())
        addressNotificationDetail.text = notification?.address
        descrNotificationDetail.setText(notification?.description)

        backToAllNotifications.setOnClickListener {
            startActivity(Intent(this, AllNotifications::class.java))
        }
    }

    override fun onStart() {
        super.onStart()

        if(Data.loggedUser == null)
            startActivity(Intent(this, Login::class.java))

        if(Data.loggedUser?.id == null)
            startActivity(Intent(this, Login::class.java))
    }
}