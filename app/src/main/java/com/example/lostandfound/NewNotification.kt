package com.example.lostandfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.lostandfound.entity.Data
import com.example.lostandfound.entity.NotificationEntity
import com.example.lostandfound.net.retrofit.model.NotificationRequest
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class NewNotification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_notification)

        val button = findViewById<Button>(R.id.notifButton)

        button.setOnClickListener({
            val notifReq = collectData()
            GlobalScope.launch { pushNotification(notifReq) }
        })

        val date = findViewById<TextView>(R.id.dateNew)

        val calendar = Calendar.getInstance()
        date.setText(
            calendar.get(Calendar.YEAR).toString().padStart(4, '0')
                    + (calendar.get(Calendar.MONTH) + 1).toString().padStart(2, '0')
                    + calendar.get(Calendar.DAY_OF_MONTH).toString().padStart(2, '0')
        )
    }

    fun collectData(): NotificationRequest?{
        val title = findViewById<TextView>(R.id.titleNew).text.toString()
        val subject = findViewById<TextView>(R.id.subjectNew).text.toString()
        val description = findViewById<TextView>(R.id.descriptionNew).text.toString()
        val address = findViewById<TextView>(R.id.addressNew).text.toString()
        val date = findViewById<TextView>(R.id.dateNew).text.toString()

        val dateFormat: String? = ((date.get(0)).toString()).plus(date.get(1)).plus(date.get(2))
            .plus(date.get(3)).plus("-").plus(date.get(4)).plus(date.get(5))
            .plus("-").plus(date.get(6)).plus(date.get(7))



        if(title == null || subject == null || description == null
            || address == null || date == null){
            empty()
            return null
        }

        return NotificationRequest(title, subject, description, address, dateFormat )
    }

    suspend fun pushNotification(notifReq: NotificationRequest?){

        if(notifReq == null)
            return

        val call = (GlobalScope.async {
            Data.service.pushNotification(notifReq, Data.loggedUser!!.username)
        }).await()

        call.enqueue(object: Callback<NotificationEntity>{
            override fun onResponse(
                call: Call<NotificationEntity>,
                response: Response<NotificationEntity>
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

            override fun onFailure(call: Call<NotificationEntity>?, t: Throwable?) {
                val text = "Something went wrong"
                val length = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, length)
                toast.show()
                return
            }

        })
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