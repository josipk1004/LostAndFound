package com.example.lostandfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lostandfound.entity.Data
import com.example.lostandfound.entity.Notification
import com.example.lostandfound.entity.ShortNotification
import com.example.lostandfound.net.retrofit.model.NotificationResponse
import kotlinx.android.synthetic.main.activity_user_notifications.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class UserNotifications : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_notifications)

        if(Data.loggedUser == null)
            startActivity(Intent(this, Login::class.java))

        if(Data.loggedUser?.id == null)
            startActivity(Intent(this, Login::class.java))

        val call = Data.service.getUserNotifications(Data.loggedUser!!.username)
        call.enqueue(object: Callback<List<Notification>>{
            override fun onResponse(
                call: Call<List<Notification>>?,
                response: Response<List<Notification>>
            ) {
                if(response.code() == 200){
                    val shortNotifications = mutableListOf<ShortNotification>()
                    response.body().forEach{
                        shortNotifications.add(it as ShortNotification)
                    }
                    showData(shortNotifications)
                } else {
                    val text = "Something went wrong"
                    val length = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(applicationContext, text, length)
                    toast.show()
                    return
                }
            }

            override fun onFailure(call: Call<List<Notification>>?, t: Throwable?) {
                Toast.makeText(applicationContext, t?.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun showData(notifications: List<ShortNotification>) {
        /*userNotificationsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@UserNotifications)
            adapter = NotificationsAdapter(notifications)
        }*/
        userNotificationsRecyclerView.layoutManager = LinearLayoutManager(this@UserNotifications)
        var adapter = NotificationsAdapter(notifications)
        userNotificationsRecyclerView.adapter = adapter

        adapter.setOnItemClickListener(object: NotificationsAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@UserNotifications, UserNotificationDetails::class.java)
                    .putExtra("id", notifications[position].id!!)
                startActivity(intent)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        if(Data.loggedUser == null)
            startActivity(Intent(this, Login::class.java))

        if(Data.loggedUser?.id == null)
            startActivity(Intent(this, Login::class.java))
    }
}