package com.example.lostandfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lostandfound.entity.Data
import com.example.lostandfound.entity.User
import com.example.lostandfound.net.retrofit.apiClient.ApiClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val service = ApiClient.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tokenExists = false

        if(Data.loggedUser == null)
            goToLogin()
        nameMain.setText(Data.loggedUser?.firstName + "!")


        newNotifButton.setOnClickListener {
            val intent = Intent(this@MainActivity, NewNotification::class.java)
            startActivity(intent)
        }

        myNotificationsbutton.setOnClickListener {
            val intent = Intent(this@MainActivity, UserNotifications::class.java)
            startActivity(intent)
        }

        allNotifications.setOnClickListener {
            val intent = Intent(this@MainActivity, AllNotifications::class.java)
            startActivity(intent)
        }

        logoutButton.setOnClickListener {
            Data.loggedUser = null
            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
        }
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("failed", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token
                if(Data.loggedUser != null) {
                    try {
                        GlobalScope.launch {
                            val ser = Data.service.setToken(token, Data.loggedUser?.username)
                            ser.enqueue(object : Callback<User> {
                                override fun onResponse(
                                    call: Call<User>?,
                                    response: Response<User>?
                                ) {
                                    print("ab")
                                }

                                override fun onFailure(call: Call<User>?, t: Throwable?) {
                                    print("abc")
                                }

                            })
                        }
                    } catch (e: Exception) {
                        print(e.toString())
                    }
                }

                // Log and toast
                Log.e("token", token!!)
                tokenExists = true
            })

    }

    fun goToLogin(){
        startActivity(Intent(this, Login::class.java))
    }

    override fun onResume(){
        super.onResume()
        if(Data.loggedUser == null)
            goToLogin()
        nameMain.setText(Data.loggedUser?.firstName + "!")
    }


    override fun onStart() {
        super.onStart()
        if(Data.loggedUser == null)
            startActivity(Intent(this@MainActivity, Login::class.java))
    }

    companion object {
        val ACTION_RESPONSE = "messageReceived"
    }
}