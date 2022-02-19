package com.example.lostandfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.lostandfound.net.retrofit.apiClient.ApiClient
import com.example.lostandfound.net.retrofit.model.RegisterRequest
import com.example.lostandfound.net.retrofit.model.RegisterResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val button = findViewById<Button>(R.id.registerButtonRegister)

        button.setOnClickListener{
            register()
        }
    }

    fun register(){
        val registerRequest = makeRegisterRequest()

        val api = ApiClient.create().registerUser(registerRequest)

        api.enqueue(object: retrofit2.Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>?,
                response: Response<RegisterResponse>?
            ) {
                goToLogin()
            }

            override fun onFailure(call: Call<RegisterResponse>?, t: Throwable?) {
                val text = "Failure!!!"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }

        })
    }

    fun makeRegisterRequest() : RegisterRequest{
        val firstName = findViewById<TextView>(R.id.firstNameRegister).text.toString()
        val lastName = findViewById<TextView>(R.id.lastNameRegister).text.toString()
        val email = findViewById<TextView>(R.id.emailRegister).text.toString()
        val username = findViewById<TextView>(R.id.usernameRegister).text.toString()
        val password = findViewById<TextView>(R.id.passwordRegister).text.toString()

        return RegisterRequest(firstName, lastName, email, username, password)

    }

    fun goToLogin(){
        startActivity(Intent(this, Login::class.java))
    }
}