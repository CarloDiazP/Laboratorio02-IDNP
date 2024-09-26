package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityAccountBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.gson.Gson

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding
    lateinit var gson : Gson
    private lateinit var data: Intent

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
            val accountEntity = AccountEntity(firstName = firstname, lastName = lastname, email = email, phone = phone, username = username, password = password )
            gson = Gson()
            val accountJson = gson.toJson(accountEntity)
            data = Intent()

            data.putExtra("ACCOUNT_RECORD", accountJson )
            setResult(100, data )
            finish()

        }
        btnCancel.setOnClickListener {
            setResult(200)
            finish()
        }
    }
}