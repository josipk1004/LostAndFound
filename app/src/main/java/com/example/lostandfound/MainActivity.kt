package com.example.lostandfound

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lostandfound.entity.ShortNotification
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
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

        goToLogin()
    }

    fun goToLogin(){
        startActivity(Intent(this, Login::class.java))
    }
}