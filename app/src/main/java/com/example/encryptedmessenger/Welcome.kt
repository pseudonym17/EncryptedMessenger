package com.example.encryptedmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        supportActionBar?.hide()

        val loginbtn = findViewById<Button>(R.id.loginbtn)
        val regbtn = findViewById<Button>(R.id.registerbtn)
        val logo = findViewById<ImageView>(R.id.logo)

        loginbtn.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        regbtn.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        logo.setOnClickListener{
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

    }
}