package com.example.lostandfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.lostandfound.entity.Data
import com.example.lostandfound.entity.User
import com.example.lostandfound.net.retrofit.model.RegisterRequest
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val button = findViewById<Button>(R.id.registerButtonRegister)

        button.setOnClickListener{
            GlobalScope.launch { register() }
        }
    }

    suspend fun register(){
        val registerRequest = makeRegisterRequest()

        if(registerRequest == null){
            return
        }

        val serviceCall = (GlobalScope.async {  Data.service.registerUser(registerRequest)})
            .await()

        serviceCall.enqueue(object: retrofit2.Callback<User>{
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if(response.code() == 200){
                    goToLogin()
                } else if (response.code() == 403){
                    val text = "Username already exists!!!"
                    val duration = Toast.LENGTH_SHORT

                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                    return
                } else {
                    val text = "Something went wrong!!!"
                    val duration = Toast.LENGTH_SHORT

                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                    return
                }
            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {
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
        }; if(password.length <= 8){
            val text = "Password length must be at least 8!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
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
        return
    }
}