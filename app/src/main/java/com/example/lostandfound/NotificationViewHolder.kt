package com.example.lostandfound

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.graphics.Typeface
import androidx.recyclerview.widget.RecyclerView
import com.example.lostandfound.NotificationListItem
import com.example.lostandfound.R
import com.example.lostandfound.entity.ShortNotification

class NotificationViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.notification_item, parent, false)
) {
    var notification: ShortNotification? = null
        private set
    private val titleView = itemView.findViewById<TextView>(R.id.title)
    private val subjectView = itemView.findViewById<TextView>(R.id.subject)

    fun bindTo(item: NotificationListItem?) {
        if (item is NotificationListItem.Separator) {
            titleView.text = "${item.title} obavijesti"
            titleView.setTypeface(null, Typeface.BOLD)
            subjectView.text = "${item.subject} subjekat"
        } else {
            titleView.text = item?.title
            titleView.setTypeface(null, Typeface.NORMAL)
            subjectView.text = item?.subject
        }
        notification = (item as? NotificationListItem.Item)?.notification
        titleView.text = item?.title
        subjectView.text = item?.subject
    }
}
