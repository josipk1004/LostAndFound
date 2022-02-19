package com.example.lostandfound

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lostandfound.databinding.*
import androidx.activity.viewModels

class MyNotifications : AppCompatActivity() {
    lateinit var binding: ActivityMyNotificationsBinding
    private set
//    private val viewModel by viewModels<NotificationViewModel> {
//        NotificationViewModelFactory(application)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        super.onCreate(savedInstanceState)
//        binding = ActivityMyNotificationsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_my_notifications)
//        setContentView(binding.root)
//
//        val adapter = NotificationAdapter()
//        binding.myNotificationsList.adapter = adapter


    }
}