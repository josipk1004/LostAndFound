package com.example.lostandfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lostandfound.entity.Data
import com.example.lostandfound.entity.User
import com.example.lostandfound.net.retrofit.model.LoginRequest
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if(Data.loggedUser?.id != null)
            startActivity(Intent(this@Login, MainActivity::class.java))

        registerButtonLogin.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }

        loginButton.setOnClickListener {
            val username = usernameLogin.text.toString().trim()
            val password = passwordLogin.text.toString().trim()
            if(username.isEmpty()){
                usernameLogin.error = "Username required"
                usernameLogin.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                passwordLogin.error = "Password required"
                passwordLogin.requestFocus()
                return@setOnClickListener
            }

            val loginRequest = makeLoginRequest()
            val serviceCall = Data.service.loginUser(loginRequest)
            serviceCall.enqueue(object: Callback<User> {
                    override fun onResponse(
                        call: Call<User>?,
                        response: Response<User>?
                    ) {
                        if(response?.isSuccessful == true){
                            Data.setUser(response.body())
                            startActivity(Intent(this@Login, MainActivity::class.java))
                        }else{
                            if(response?.code() == 404){
                                val text = "Wrong username or password, please try again or register!"
                                Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
                            }
                            else {
                                val text = "An error occurred, please try again!"
                                Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<User>?, t: Throwable?) {
                        Toast.makeText(applicationContext, t?.message, Toast.LENGTH_LONG).show()
                    }
                })
        }
    }

    fun makeLoginRequest() : LoginRequest {
        val username = usernameLogin.text.toString().trim()
        val password = passwordLogin.text.toString().trim()

        return LoginRequest(username, password)
    }

    override fun onStart() {
        super.onStart()

        if(Data.loggedUser?.id != null)
            startActivity(Intent(this@Login, MainActivity::class.java))
    }
}