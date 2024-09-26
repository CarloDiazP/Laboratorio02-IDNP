package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnLogin = binding.btnLogin
        val btnAddAccount = binding.btnAddAccount


        btnLogin.setOnClickListener {
            val userName = binding.userName.text.toString()
            val password = binding.password.text.toString()
            if (userName =="admin" && password == "admin"){
                Toast.makeText(getApplicationContext(),"Bienvenido a mi app", Toast.LENGTH_SHORT).show()
                Log.d("Login successful", "Bienvenido a mi app")
            }else {
                Toast.makeText(getApplicationContext(),"Error en la autenticacion", Toast.LENGTH_SHORT).show()
                Log.d("Login failed", "Error en la autenticacion")
            }
        }

        btnAddAccount.setOnClickListener {
            val intent = Intent(applicationContext, AccountActivity::class.java)
            startActivity(intent)
        }

    }
}