package com.example.lostandfound

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lostandfound.entity.ShortNotification
import kotlinx.android.synthetic.main.notification_row.view.*

class NotificationsAdapter(private val notifications: List<ShortNotification>): RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {
    private lateinit var nListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        nListener = listener
    }

    class ViewHolder(itemView: View, listener: OnItemClickListener): RecyclerView.ViewHolder(itemView) {
        val titleNotification: TextView = itemView.titleNotification

        init{
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_row, parent, false)
        return ViewHolder(view, nListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleNotification.text = notifications[position].toString()
    }

    override fun getItemCount() = notifications.size
}