package com.example.encryptedmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
//import com.example.encryptedmessenger.databinding.ActivityLoginBinding
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.DatabaseReference


class Login : AppCompatActivity() {

    //private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.loginbtn)

        button.setOnClickListener{
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
    }
}