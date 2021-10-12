package com.example.encryptedmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val button = findViewById<Button>(R.id.registerbtn)

        button.setOnClickListener{
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
    }
}