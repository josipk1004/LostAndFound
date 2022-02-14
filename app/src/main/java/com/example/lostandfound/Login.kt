package com.example.lostandfound

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val registAct = Intent(this , Register::class.java)

        val button = findViewById<Button>(R.id.register)
        button.setOnClickListener { v: View ->

                startActivity(registAct)

        }

    }
}