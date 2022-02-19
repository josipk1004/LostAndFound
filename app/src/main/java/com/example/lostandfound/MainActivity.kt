package com.example.lostandfound

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lostandfound.entity.ShortNotification
import com.example.lostandfound.net.retrofit.apiClient.ApiClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // data:
    val service = ApiClient.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //nameMain =

        newNotifButton.setOnClickListener {
            val intent = Intent(this@MainActivity, NewNotification::class.java)
            startActivity(intent)
        }

        myNotificationsbutton.setOnClickListener {
            val intent = Intent(this@MainActivity, MyNotifications::class.java)
            startActivity(intent)
        }

        allNotifications.setOnClickListener {
            val intent = Intent(this@MainActivity, AllNotifications::class.java)
            startActivity(intent)
        }

        goToRegister()
    }

    fun goToRegister(){
        startActivity(Intent(this, Register::class.java))
    }
}