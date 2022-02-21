package com.example.lostandfound

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lostandfound.entity.Data
import com.example.lostandfound.entity.NotificationEntity
import com.example.lostandfound.entity.ShortNotification
import com.example.lostandfound.recycleView.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_user_notifications.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserNotifications : AppCompatActivity() {


    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_user_notifications)

            layoutManager = LinearLayoutManager(applicationContext)
            userNotificationsRecyclerView.layoutManager = layoutManager

            adapter = RecyclerAdapter()
            userNotificationsRecyclerView.adapter = adapter


            if (Data.loggedUser == null)
                startActivity(Intent(this, Login::class.java))

            if (Data.loggedUser?.id == null)
                startActivity(Intent(this, Login::class.java))

            GlobalScope.launch { collectData() }

    }



    override fun onStart() {
        super.onStart()
        if(Data.loggedUser == null)
            startActivity(Intent(this, Login::class.java))
    }

    override fun onResume(){
        super.onResume()
        GlobalScope.launch { collectData() }
    }

    suspend fun collectData(){
        val call = (GlobalScope.async {
            Data.service.getUserNotifications(Data.loggedUser!!.username)
        }).await()
        call.enqueue(object: Callback<List<NotificationEntity>>{
            override fun onResponse(
                call: Call<List<NotificationEntity>>?,
                response: Response<List<NotificationEntity>>
            ) {
                if(response.code() == 200){
                    val shortNotifications = mutableListOf<ShortNotification>()
                    response.body().forEach{
                        val shortNotif = ShortNotification(it)
                        shortNotifications.add(shortNotif)
                    }
                    showData(shortNotifications)
                } else {
                    val text = "Something went wrong"
                    val length = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(applicationContext, text, length)
                    toast.show()
                    return
                }
            }

            override fun onFailure(call: Call<List<NotificationEntity>>?, t: Throwable?) {
                Toast.makeText(applicationContext, t?.message, Toast.LENGTH_LONG).show()
            }

        })

    }

    fun showData(notifications: List<ShortNotification>?){
        layoutManager = LinearLayoutManager(applicationContext)
        userNotificationsRecyclerView.layoutManager = layoutManager

        val adapter = RecyclerAdapter()
        adapter.setNotifs(notifications)

        adapter.setOnItemClickListener(object: RecyclerAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@UserNotifications, UserNotificationDetails::class.java)
                    .putExtra("id", notifications!!.get(position).id!!)
                startActivity(intent)
            }
        })
        userNotificationsRecyclerView.adapter = adapter


    }
}