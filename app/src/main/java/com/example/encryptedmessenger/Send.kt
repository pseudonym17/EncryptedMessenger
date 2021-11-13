package com.example.encryptedmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.encryptedmessenger.databinding.ActivityMenuBinding
import com.example.encryptedmessenger.databinding.ActivitySendBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Send : AppCompatActivity()
{
    private lateinit var sendRecyclerView: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send)

        var contact = Singleton.currentContact
        Toast.makeText(this, "Contact: $contact", Toast.LENGTH_SHORT).show()

        sendRecyclerView = findViewById(R.id.sendRecyclerView)
        messageBox = findViewById(R.id.messageBox)
        sendButton = findViewById(R.id.sendButton)
    }
}