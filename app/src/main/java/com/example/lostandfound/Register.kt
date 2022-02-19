package com.example.lostandfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.lostandfound.entity.Data
import com.example.lostandfound.net.retrofit.apiClient.ApiClient
import com.example.lostandfound.net.retrofit.model.RegisterRequest
import com.example.lostandfound.net.retrofit.model.RegisterResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val button = findViewById<Button>(R.id.registerButtonRegister)

        button.setOnClickListener({
            register()
        })
    }

    fun register(){
        val registerRequest = makeRegisterRequest()

        if(registerRequest == null){
            return
        }

        val serviceCall = Data.service.registerUser(registerRequest)

        serviceCall.enqueue(object: retrofit2.Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if(response.code() == 200){
                    goToLogin()
                } else {
                    val text = "Something went wrong!!!"
                    val duration = Toast.LENGTH_SHORT

                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                    return
                }
            }

            override fun onFailure(call: Call<RegisterResponse>?, t: Throwable?) {
                val text = "Something went wrong!!!"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                return
            }

        })
    }

    fun makeRegisterRequest() : RegisterRequest?{
        val firstName = findViewById<TextView>(R.id.firstNameRegister).text.toString()
        val lastName = findViewById<TextView>(R.id.lastNameRegister).text.toString()
        val email = findViewById<TextView>(R.id.emailRegister).text.toString()
        val username = findViewById<TextView>(R.id.usernameRegister).text.toString()
        val password = findViewById<TextView>(R.id.passwordRegister).text.toString()

        if(firstName == null || lastName == null || email == null
            || username == null || password == null){
            fieldEmpty()
            return null
        }

        return RegisterRequest(firstName, lastName, email, username, password)

    }

    fun goToLogin(){
        startActivity(Intent(this, Login::class.java))
    }

    fun fieldEmpty(){
        val text = "All fields must be filled!!!"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
}