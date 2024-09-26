package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
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
            if (userExists(userName, password)){
                val intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
            }else {
                Toast.makeText(getApplicationContext(),"Cuenta no encontrada", Toast.LENGTH_SHORT).show()
                Log.d("Login failed", "Cuenta no encontrada")
            }
        }

        btnAddAccount.setOnClickListener {
            val intent = Intent(applicationContext, AccountActivity::class.java)
            startActivity(intent)
        }

    }

    fun userExists(userName:String, password:String): Boolean {
        // TODO: leer cuenta.txt para ver si esta registrado
        return true
    }
}