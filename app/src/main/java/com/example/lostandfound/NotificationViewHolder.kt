package com.example.lostandfound

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.graphics.Typeface
import androidx.recyclerview.widget.RecyclerView

class NotificationViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.notification_item, parent, false)
) {
    var notification: Notification? = null
        private set
    private val titleView = itemView.findViewById<TextView>(R.id.title)
    private val userView = itemView.findViewById<TextView>(R.id.user)

    fun bindTo(item: NotificationListItem?) {
        if (item is NotificationListItem.Separator) {
            titleView.text = "${item.title} obavijesti"
            titleView.setTypeface(null, Typeface.BOLD)
            userView.text = "${item.user}"
        } else {
            titleView.text = item?.title
            titleView.setTypeface(null, Typeface.NORMAL)
            userView.text = item?.user
        }
        notification = (item as? NotificationListItem.Item)?.notification
        titleView.text = item?.title
        userView.text = item?.user
    }
}
