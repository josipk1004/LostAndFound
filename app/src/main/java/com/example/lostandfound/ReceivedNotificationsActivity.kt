package com.example.lostandfound

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.lostandfound.databinding.ActivityMainBinding
import com.example.lostandfound.databinding.ActivityReceivedNotificationsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ReceivedNotificationsActivity : AppCompatActivity() {
    lateinit var binding: ActivityReceivedNotificationsBinding
    private set
    private val viewModel by viewModels<NotificationViewModel> {
        NotificationViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceivedNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = NotificationAdapter()
        binding.notificationList.adapter = adapter

        lifecycleScope.launch {
            viewModel.allNotifications.collectLatest { adapter.submitData(it) }
        }

        initAddButtonListener()
        initSwipeToDelete()
    }

    private fun initSwipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val notificationViewHolder = viewHolder as NotificationViewHolder
                return if (notificationViewHolder.notification != null) {
                    makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
                } else {
                    makeMovementFlags(0, 0)
                }
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (viewHolder as NotificationViewHolder).notification?.let {
                    viewModel.remove(it)
                }
            }
        }).attachToRecyclerView(binding.notificationList)
    }

    private fun addNotification() {
        val newNotification = binding.inputText.text.trim()
        if (newNotification.isNotEmpty()) {
            viewModel.insert(newNotification)
            binding.inputText.setText("")
        }
    }

    private fun initAddButtonListener() {
        binding.addButton.setOnClickListener {
            addNotification()
        }

        binding.inputText.setOnEditorActionListener {
            _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addNotification()
                return@setOnEditorActionListener true
            }
            false
        }
        binding.inputText.setOnKeyListener {
            _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                addNotification()
                return@setOnKeyListener true
            }
            false
        }
    }
}