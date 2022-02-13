package com.example.lostandfound

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class NotificationViewModelFactory(
    private val app: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotificationViewModel::class.java)) {
            val notificationDao = NotificationDb.get(app).notificationDao()
            @Suppress("UNCHECKED_CAST")
            return NotificationViewModel(notificationDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}