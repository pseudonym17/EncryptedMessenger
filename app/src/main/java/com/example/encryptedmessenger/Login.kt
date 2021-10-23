package com.example.encryptedmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.encryptedmessenger.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth




class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button = findViewById<Button>(R.id.loginbtn)

        button.setOnClickListener{
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
        mAuth = FirebaseAuth.getInstance()

        binding.loginbtn.setOnClickListener {
            loginUser()
        }
    }
    private fun loginUser()
    {
        val username: String = binding.username.text.toString()
        val password: String = binding.password.text.toString()

        if (username == "")
        {
            Toast.makeText(this@Login, "Please Enter Username.", Toast.LENGTH_LONG).show()
        }
        else if (password == "")
        {
            Toast.makeText(this@Login, "Please Enter Password.", Toast.LENGTH_LONG).show()
        }
        else
        {
            mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful)
                    {
                        val intent = Intent(this@Login, Menu::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    }
                    else
                    {
                        Toast.makeText(
                            this@Login,
                            "Error Message: " + task.exception!!.message.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }
        }
    }
}