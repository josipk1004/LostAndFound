package com.example.lostandfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val registAct = Intent(this , Register::class.java)

        val button = findViewById<Button>(R.id.regButt)
        button.setOnClickListener { v: View ->
            {
                startActivity(registAct)
            }
        }
    }
}