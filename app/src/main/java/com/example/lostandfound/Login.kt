package com.example.lostandfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val main = Intent(this , MainActivity::class.java)

        loginButton.setOnClickListener { v: View ->
            {

                startActivity(main)
            }
        }
    }

}