package com.example.lostandfound

import android.app.AlertDialog
import android.app.Notification
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lostandfound.entity.Data
import com.example.lostandfound.entity.NotificationEntity
import com.example.lostandfound.net.retrofit.model.DeleteNotifResponse
import kotlinx.android.synthetic.main.activity_user_notification_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserNotificationDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_notification_details)

        val id = intent.getSerializableExtra("id") as Long
        var notification: NotificationEntity? = null

        val callNotif = Data.service.getNotification(id)

        callNotif.enqueue(object : Callback<NotificationEntity> {
            override fun onResponse(
                call: Call<NotificationEntity>?,
                response: Response<NotificationEntity>
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

            override fun onFailure(call: Call<com.example.lostandfound.entity.NotificationEntity>?, t: Throwable?) {
                Toast.makeText(applicationContext, t?.message, Toast.LENGTH_LONG).show()
            }
        })

        titleUserNotificationDetail.text = notification?.title
        subjectUserNotificificationDetail.text = notification?.subject
        dateUserNotificatonDetail.setText(notification?.date.toString())
        addressUserNotificationDetail.text = notification?.address
        descrUserNotificationDetail.setText(notification?.description)

        backToUserNotifications.setOnClickListener {
            startActivity(Intent(this, UserNotifications::class.java))
        }

        editUserNotificationDetail.setOnClickListener {
            startActivity(Intent(this, EditNotification::class.java).putExtra("id", id))
        }

        deleteUserNotificationDetail.setOnClickListener {
            deleteNotif(id)
        }
    }

    private fun deleteNotif(id: Long){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete " + titleUserNotificationDetail.text.toString())
        builder.setMessage("Are you sure you want to delete this notification?")

        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.ok, Toast.LENGTH_SHORT).show()

            val callDeleteNotif = Data.service.deleteNotification(id)
            callDeleteNotif.enqueue(object: Callback<DeleteNotifResponse> {
                override fun onResponse(
                    call: Call<DeleteNotifResponse>?,
                    response: Response<DeleteNotifResponse>
                ) {
                    if(response.code() == 200){
                        Toast.makeText(applicationContext, "Deleted successfully!", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(applicationContext, "Deleting is not possible!", Toast.LENGTH_SHORT).show()
                    }
                    startActivity(Intent(this@UserNotificationDetails, UserNotifications::class.java))
                }

                override fun onFailure(call: Call<DeleteNotifResponse>?, t: Throwable?) {
                    Toast.makeText(applicationContext, t?.message, Toast.LENGTH_SHORT).show()
                }
            })
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.cancel, Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    override fun onStart() {
        super.onStart()
        if(Data.loggedUser == null)
            startActivity(Intent(this, Login::class.java))

        if(Data.loggedUser?.id == null)
            startActivity(Intent(this, Login::class.java))
    }
}