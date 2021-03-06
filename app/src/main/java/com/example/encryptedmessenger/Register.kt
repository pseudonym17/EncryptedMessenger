package com.example.encryptedmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.graphics.Color
import android.view.View.inflate
import android.widget.Toast
import com.example.encryptedmessenger.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserID: String = ""
    var ref = FirebaseDatabase.getInstance().getReference("Users")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "Register"
//        toolbar.setTitleTextColor(Color.WHITE)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mAuth = FirebaseAuth.getInstance()

        binding.registerbtn.setOnClickListener {
            registerUser()
        }

        supportActionBar?.hide()

        val button = findViewById<Button>(R.id.registerbtn)

        button.setOnClickListener{
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }


        mAuth = FirebaseAuth.getInstance()

        binding.registerbtn.setOnClickListener {
            registerUser()
        }

    }
    private fun registerUser() {
        val username: String = binding.editTextTextPersonName.text.toString()
        val email: String = binding.editTextTextEmailAddress.text.toString()
        val password: String = binding.editTextTextPassword.text.toString()

        if (username == "") {
            Toast.makeText(this@Register, "please enter username.", Toast.LENGTH_LONG)
                .show()
        }
        else if (email == "") {
            Toast.makeText(this@Register, "please enter email.", Toast.LENGTH_LONG)
                .show()
        }
        else if (password == "") {
            Toast.makeText(this@Register, "please enter password.", Toast.LENGTH_LONG).show()
        }
        else {
            // Check for unique username
            var unique : Boolean = true
            val listener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (child in snapshot.children) {
                        var user = child.child("username").value.toString()
                        if (username == user) {
                            unique = false
                            Toast.makeText(this@Register, "Username already exists", Toast.LENGTH_LONG).show()
                            return
                        }
                    }
                    if (unique) {
                        mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    firebaseUserID = mAuth.currentUser!!.uid
                                    refUsers =
                                        FirebaseDatabase.getInstance().reference.child("Users")
                                            .child(firebaseUserID)

                                    // Save username
                                    Singleton.username = username

                                    val userHashMap = HashMap<String, Any>()
                                    userHashMap["uid"] = firebaseUserID
                                    userHashMap["username"] = username
//                    userHashMap["profile"] =
//                        "https://firebasestorage.googleapis.com/v0/b/messengerapp-97e68.appspot.com/o/empty_pofile_image.png?alt=media&token=90b67474-c112-4c01-b1ee-4283c3166c90"
//                    userHashMap["cover"] =
//                        "https://firebasestorage.googleapis.com/v0/b/messengerapp-97e68.appspot.com/o/OIP.jfif?alt=media&token=763c46f6-9de6-4dda-b12e-47748f5260ab"
                                    userHashMap["status"] = "offline"
//                    userHashMap["search"] = username.lowercase()
//                    userHashMap["facebook"] = "https://m.facebook.com"
//                    userHashMap["instagram"] = "https://m.instagram.com"
//                  userHashMap["website"] = "https://www.google.com"

                                    refUsers.updateChildren(userHashMap)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                val intent = Intent(this@Register, Menu::class.java)
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                                startActivity(intent)
                                                finish()
                                            }
                                        }
                                } else {
                                    Toast.makeText(
                                        this@Register,
                                        "Error Message." + task.exception!!.message.toString(),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    println("ERROR: $error")
                }
            }
            ref.addValueEventListener(listener)

        }
    }
}