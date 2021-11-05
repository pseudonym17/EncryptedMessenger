package com.example.encryptedmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import com.example.encryptedmessenger.databinding.ActivityReceiveBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class Receive : AppCompatActivity() {
    private lateinit var binding: ActivityReceiveBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiveBinding.inflate(layoutInflater)
        supportActionBar?.hide()

        binding.button.setOnClickListener {
            val receiver = Singleton.username
            val sender: String = binding.contact.text.toString()
            val conversation = sender + "-" + receiver
            var message = ""
            database = FirebaseDatabase.getInstance().getReference("Messages")

            val listener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (child in snapshot.children) {
                        var key = child.key.toString()
                        if (key == "message") {
                            message = child.getValue().toString()
                            println("Message: $message")
                            binding.message.text = message
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    println("ERROR: $error")
                }
            }
            database.child(conversation).addValueEventListener(listener)
        }

        binding.logo.setOnClickListener {
            intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}

