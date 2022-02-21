package com.example.lostandfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lostandfound.entity.Data
import com.example.lostandfound.net.retrofit.apiClient.ApiClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val service = ApiClient.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(Data.loggedUser == null)
            goToLogin()
        nameMain.setText(Data.loggedUser?.firstName + "!")

        newNotifButton.setOnClickListener {
            val intent = Intent(this@MainActivity, NewNotification::class.java)
            startActivity(intent)
        }

        myNotificationsbutton.setOnClickListener {
            val intent = Intent(this@MainActivity, UserNotifications::class.java)
            startActivity(intent)
        }

        allNotifications.setOnClickListener {
            val intent = Intent(this@MainActivity, AllNotifications::class.java)
            startActivity(intent)
        }

        logoutButton.setOnClickListener {
            Data.loggedUser = null
            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
        }

    }

    fun goToLogin(){
        startActivity(Intent(this, Login::class.java))
    }

    override fun onResume(){
        super.onResume()
        if(Data.loggedUser == null)
            goToLogin()
        nameMain.setText(Data.loggedUser?.firstName + "!")
    }


    override fun onStart() {
        super.onStart()
        if(Data.loggedUser == null)
            startActivity(Intent(this@MainActivity, Login::class.java))
    }
}