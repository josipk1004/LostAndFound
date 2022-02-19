package com.example.lostandfound

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

class NotificationAdapter : PagingDataAdapter<NotificationListItem.Item, NotificationViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(parent)
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<NotificationListItem.Item>() {
            override fun areItemsTheSame(
                oldItem: NotificationListItem.Item,
                newItem: NotificationListItem.Item
            ): Boolean {
//                return if (oldItem is NotificationListItem.Item && newItem is NotificationListItem.Item) {
                  return oldItem.notification == newItem.notification
//                } else if (oldItem is NotificationListItem.Separator && newItem is NotificationListItem.Separator) {
//                    oldItem.title == newItem.title
//                } else {
                    oldItem == newItem
//                }
            }

            override fun areContentsTheSame(
                oldItem: NotificationListItem.Item,
                newItem: NotificationListItem.Item
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}