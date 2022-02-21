package com.example.lostandfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lostandfound.entity.Data
import com.example.lostandfound.entity.NotificationEntity
import com.example.lostandfound.net.retrofit.model.NotificationRequest
import kotlinx.android.synthetic.main.activity_edit_notification.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditNotification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_notification)
        if(Data.loggedUser == null)
            startActivity(Intent(this, Login::class.java))

        val id = intent.getSerializableExtra("id") as Long

        saveEditNotificationButton.setOnClickListener {
            val title = titleEditNotification.text.toString().trim()
            val subject = subjectEditNotification.text.toString().trim()
            val date = dateEditNotification.text
            val address = addressEditNotification.text.toString().trim()
            val description = descrEditNotification.text.toString().trim()

            if(title.isEmpty()){
                titleEditNotification.error = "Title required"
                titleEditNotification.requestFocus()
                return@setOnClickListener
            }
            if(subject.isEmpty()){
                subjectEditNotification.error = "Subject required"
                subjectEditNotification.requestFocus()
                return@setOnClickListener
            }

            if(date.isEmpty()){
                dateEditNotification.error = "Date required"
                dateEditNotification.requestFocus()
                return@setOnClickListener
            }

            if(address.isEmpty()){
                addressEditNotification.error = "Address required"
                addressEditNotification.requestFocus()
                return@setOnClickListener
            }

            if(description.isEmpty()){
                descrEditNotification.error = "Description required"
                descrEditNotification.requestFocus()
                return@setOnClickListener
            }

            val notificationRequest = makeNotificationRequest()
            val serviceCall = Data.service.updateNotification(id, notificationRequest)
            serviceCall.enqueue(object: Callback<NotificationEntity> {
                override fun onResponse(
                    call: Call<NotificationEntity>?,
                    response: Response<NotificationEntity>
                ) {
                    if(response.code() == 200){
                        Toast.makeText(applicationContext, "Updated successfully!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@EditNotification, UserNotificationDetails::class.java)
                            .putExtra("id", response.body().id))
                    } else {
                        Toast.makeText(applicationContext, "Deleting is not possible!", Toast.LENGTH_SHORT).show()
                        return
                    }
                }

                override fun onFailure(call: Call<NotificationEntity>?, t: Throwable?) {
                    Toast.makeText(applicationContext, t?.message, Toast.LENGTH_SHORT).show()
                    return
                }
            })
        }
    }

    fun makeNotificationRequest() : NotificationRequest {
        val title = titleEditNotification.text.toString()
        val subject = subjectEditNotification.text.toString()
        val description = descrEditNotification.text.toString()
        val address = addressEditNotification.text.toString()
        val date = dateEditNotification.text.toString()

        return NotificationRequest(title, subject, description, address, date)
    }

    override fun onStart() {
        super.onStart()

        if(Data.loggedUser == null)
            startActivity(Intent(this@EditNotification, Login::class.java))

        if(Data.loggedUser?.id == null)
            startActivity(Intent(this, Login::class.java))
    }
}