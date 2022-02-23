package com.example.lostandfound

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import com.example.lostandfound.entity.Data
import com.example.lostandfound.entity.NotificationEntity
import com.example.lostandfound.entity.User
import com.example.lostandfound.net.retrofit.model.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class setPUSH : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_push)

        val id = intent.getSerializableExtra("id") as Long

        val button = findViewById<Button>(R.id.PUSH)
        button.setOnClickListener({
            GlobalScope.launch { publish(id) }
        })
    }

    suspend fun publish(id: Long){
        var data: HashMap<String, String>? = HashMap<String, String>()

        val type1 = findViewById<TextView>(R.id.contactType1).text.toString()
        val info1 = findViewById<TextView>(R.id.contactInfo1).text.toString()
        val type2 = findViewById<TextView>(R.id.contactType2).text.toString()
        val info2 = findViewById<TextView>(R.id.contactInfo2).text.toString()

        if(info1.equals("Info") || info1.isBlank())
            data?.put(" ", " ")
        else {
            data?.put(type1, info1)
        }

        if(info2.equals("Info") || info2.isBlank())
            data?.put(" ", " ")
        else {
            data?.put(type2, info2)
        }

        val notifRes = Data.service.getNotification(id)
        notifRes.enqueue(object: Callback<NotificationEntity>{
            override fun onResponse(
                call: Call<NotificationEntity>?,
                response: Response<NotificationEntity>?
            ) {
                val notif = response?.body()
                val note = Note(notif?.subject, findViewById<TextView>(R.id.description).text.toString()
                ,data)
                val tokenResponse = Data.service.getUserToken(notif?.username)
                tokenResponse.enqueue(object: Callback<String>{
                    override fun onResponse(call: Call<String>?, response: Response<String>?) {
                        val pushResponse = Data.service.sendPUSH(note, response!!.body())
                        pushResponse.enqueue(object: Callback<User>{
                            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                                finish()
                            }

                            override fun onFailure(call: Call<User>?, t: Throwable?) {

                            }

                        })
                    }

                    override fun onFailure(call: Call<String>?, t: Throwable?) {

                    }

                })
            }

            override fun onFailure(call: Call<NotificationEntity>?, t: Throwable?) {

            }

        })
    }
}