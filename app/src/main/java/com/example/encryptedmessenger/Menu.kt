package com.example.encryptedmessenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.encryptedmessenger.databinding.ActivityMenuBinding
import com.google.firebase.database.DatabaseReference

class Menu : AppCompatActivity() {
    private lateinit var binding : ActivityMenuBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        binding.button.setOnClickListener {
            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show()
            binding.message.text.clear()
        }

        setContentView(binding.root)
    }
}