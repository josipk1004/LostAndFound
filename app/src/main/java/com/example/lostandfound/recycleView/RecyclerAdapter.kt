package com.example.lostandfound.recycleView

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lostandfound.EditNotification
import com.example.lostandfound.R
import com.example.lostandfound.UserNotifications
import com.example.lostandfound.entity.ShortNotification

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    private var notifs: List<ShortNotification>? = null
    private lateinit var nListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        nListener = listener
    }

    fun setNotifs(notifications: List<ShortNotification>?){
        notifs = notifications
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = (LayoutInflater.from(parent.context)).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v, nListener)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        var text: String? = notifs!!.get(position).subject
        if(text == null)
            text = " "
        holder.itemSubject.text = notifs!!.get(position).subject
        text = notifs!!.get(position).title
        if(text == null)
            text = " "
        holder.itemTitle.text = text
    }

    override fun getItemCount(): Int {
        if(notifs == null)
            return 0
        return notifs!!.count()
    }

    inner class ViewHolder(itemView: View, listener: OnItemClickListener): RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView
        var itemSubject: TextView

        init{
            itemTitle = itemView.findViewById(R.id.item_title)
            itemSubject = itemView.findViewById(R.id.item_subject)
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }

        }
    }
}