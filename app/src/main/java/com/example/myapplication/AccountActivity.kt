package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityAccountBinding
import com.example.myapplication.databinding.ActivityMainBinding

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnOk = binding.btnOK
        val btnCancel = binding.btnCancel

        // listeners
        btnOk.setOnClickListener {
            val firstname = binding.edtFirstname.text.toString()
            val lastname = binding.edtLastname.text.toString()
            val email = binding.edtEmail.text.toString()
            val phone = binding.edtPhone.text.toString()
            val username = binding.edtUsername2.text.toString()
            val password = binding.edtPassword2.text.toString()

            val info = "Nombre: $firstname\nApellidos: $lastname\nCorreo: $email\nTelefono: $phone\nUsername: $username\nPassword: $password"

            Toast.makeText(getApplicationContext(),"Cuenta registrada", Toast.LENGTH_SHORT).show()
            Log.d("Register", info)
        }
    }
}