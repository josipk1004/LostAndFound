package com.example.lostandfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.lostandfound.entity.Data
import com.example.lostandfound.net.retrofit.model.NotificationRequest
import com.example.lostandfound.net.retrofit.model.NotificationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewNotification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_notification)

        val button = findViewById<Button>(R.id.notifButton)

        button.setOnClickListener({
            pushNotification()
        })
    }

    fun pushNotification(){
        val notifReq = collectData()

        if(notifReq == null)
            return

        val call = Data.service.pushNotification(notifReq, Data.loggedUser!!.username)

        call.enqueue(object: Callback<NotificationResponse>{
            override fun onResponse(
                call: Call<NotificationResponse>,
                response: Response<NotificationResponse>
            ) {
                if(response.code() == 200){
                    goToMain()
                } else {
                    val text = "Something went wrong"
                    val length = Toast.LENGTH_SHORT

                    val toast = Toast.makeText(applicationContext, text, length)
                    toast.show()
                    return
                }
            }

            override fun onFailure(call: Call<NotificationResponse>?, t: Throwable?) {
                val text = "Something went wrong"
                val length = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, length)
                toast.show()
                return
            }

        })
    }

    fun collectData(): NotificationRequest?{
        val title = findViewById<TextView>(R.id.titleNew).text.toString()
        val subject = findViewById<TextView>(R.id.subjectNew).text.toString()
        val description = findViewById<TextView>(R.id.descriptionNew).text.toString()
        val address = findViewById<TextView>(R.id.addressNew).text.toString()
        val date = findViewById<TextView>(R.id.dateNew).text.toString()

        if(title == null || subject == null || description == null
            || address == null || date == null){
            empty()
            return null
        }

        return NotificationRequest(title, subject, description, address, date)
    }

    fun empty(){
        val text = "All fields must be filled!!"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    fun goToMain(){
        startActivity(Intent(this, MainActivity::class.java))
    }
}