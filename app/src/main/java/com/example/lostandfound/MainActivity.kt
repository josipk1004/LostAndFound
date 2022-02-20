package com.example.lostandfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lostandfound.entity.Data
import com.example.lostandfound.entity.ShortNotification
import com.example.lostandfound.net.retrofit.apiClient.ApiClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // data:

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        newNotifButton.setOnClickListener {
            val intent = Intent(this@MainActivity, NewNotification::class.java)
            startActivity(intent)
        }

   /*     myNotificationsbutton.setOnClickListener {
            val intent = Intent(this@MainActivity, MyNotifications::class.java)
            startActivity(intent)
        } */

        allNotifications.setOnClickListener {
            val intent = Intent(this@MainActivity, AllNotifications::class.java)
            startActivity(intent)
        }

        if(Data.loggedUser == null)
            goToLogin()
        nameMain.setText(Data.loggedUser?.firstName + "!")

    }

    override fun onResume(){
        super.onResume()
        if(Data.loggedUser == null)
            goToLogin()
        nameMain.setText(Data.loggedUser?.firstName + "!")
    }

    fun goToLogin(){
        startActivity(Intent(this, Login::class.java))
    }
}