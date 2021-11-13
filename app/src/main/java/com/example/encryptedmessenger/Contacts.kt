package com.example.encryptedmessenger

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.encryptedmessenger.databinding.ActivityContactsBinding
import com.example.encryptedmessenger.databinding.ActivityMenuBinding
import com.google.firebase.database.*

class Contacts : AppCompatActivity() {
    private lateinit var binding : ActivityContactsBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        database = FirebaseDatabase.getInstance().getReference("UserContacts")
        val user = Singleton.username.toString()

        var contactList: MutableList<String?> = ArrayList()
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    var contact = child.getValue().toString()
                    println("Contact: $contact")
                    var isNew = true
                    for (c in contactList) {
                        if (contact == c) {
                            isNew = false
                            break
                        }
                    }
                    if (isNew) {
                        contactList.add(contact)
                        addContact(contact)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                println("ERROR: $error")
            }
        }
        //database.child(user).addValueEventListener(listener)

        binding.addbtn.setOnClickListener {
            val contact: String = binding.newcontact.text.toString()
            database.child(user).child(contact).setValue(contact).addOnSuccessListener {
                Toast.makeText(this, "Contact Saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

            binding.newcontact.text.clear()
        }

        setContentView(binding.root)
    }

    fun addContact(contact : String?) {
        val layout = findViewById<LinearLayout>(R.id.layout)

        val contactButton = Button(this)
        contactButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        contactButton.text = contact
        contactButton.setBackgroundColor(Color.CYAN)
        layout.addView(contactButton)
        // just call message page and
        // save a contact singleton to the contact value

    }

    fun addContactButton(contactList : MutableList<String?>) {
        val layout = findViewById<LinearLayout>(R.id.layout)
        for (contact in contactList) {
            val contactButton = Button(this)
            contactButton.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            contactButton.text = contact
            contactButton.setBackgroundColor(Color.CYAN)
            layout.addView(contactButton)
        }
    }
}