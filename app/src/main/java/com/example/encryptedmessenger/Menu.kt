package com.example.encryptedmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.encryptedmessenger.databinding.ActivityMenuBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Menu : AppCompatActivity() {
    private lateinit var binding : ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        binding.sendbtn.setOnClickListener {
            val intent = Intent(this@Menu, Send::class.java)
            startActivity(intent)
        }
        binding.receivebtn.setOnClickListener {
            val intent = Intent(this@Menu, Receive::class.java)
            startActivity(intent)
        }
        binding.contactsbtn.setOnClickListener {
            val intent = Intent(this@Menu, Contacts::class.java)
            startActivity(intent)
        }
        binding.signoutbtn.setOnClickListener {
            val intent = Intent(this@Menu, Welcome::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}