package com.example.lostandfound

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.lostandfound.entity.Data
import com.example.lostandfound.entity.NotificationEntity
import com.example.lostandfound.entity.User
import com.example.lostandfound.net.retrofit.model.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class setPUSH : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_push)

        val username = intent.getSerializableExtra("user") as String

        val button = findViewById<Button>(R.id.PUSH)
        button.setOnClickListener({
            GlobalScope.launch { publish(username) }
        })
    }

    suspend fun publish(username: String){
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

        val note = Note(findViewById<TextView>(R.id.subject).text.toString(),
            findViewById<TextView>(R.id.description).text.toString(), data)
        val pushReq = (GlobalScope.async { Data.service.sendPUSH(note, username!!) }).await()
        pushReq.enqueue(object: Callback<User>{
            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                finish()
            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {

            }

        })
    }

}