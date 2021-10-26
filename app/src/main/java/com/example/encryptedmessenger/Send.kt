package com.example.encryptedmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.encryptedmessenger.databinding.ActivityMenuBinding
import com.example.encryptedmessenger.databinding.ActivitySendBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Send : AppCompatActivity() {
    private lateinit var binding: ActivitySendBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        binding.button.setOnClickListener {

            val sender = Singleton.username
            val receiver: String = binding.contact.text.toString()
            val text: String = binding.message.text.toString()
            val conversation = sender + "-" + receiver

            database = FirebaseDatabase.getInstance().getReference("Messages")
            val message = Message(sender, receiver, text)

            database.child(conversation).setValue(message).addOnSuccessListener {
                Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Message Failed", Toast.LENGTH_SHORT).show()
            }


            binding.message.text.clear()
        }

        binding.logo.setOnClickListener{
            intent = Intent(this@Send, Menu::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}