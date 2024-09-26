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

            val info = "Nombre: $firstname\nApellidos: $lastname\nCorreo: $email\nTelefono: $phone\nUsername: $username\nPassword: $password"
            val accountEntity = AccountEntity(_firstName = firstname, _lastName = lastname, _email = email, _phone = phone, _username = username, _password = password )

            gson = Gson()
            val accountJson = gson.toJson(accountEntity)

            data = Intent()

            data.putExtra("ACCOUNT_RECORD", accountJson )
            setResult(100, data )
            finish()

            //Toast.makeText(getApplicationContext(),"Cuenta registrada", Toast.LENGTH_SHORT).show()
            //Log.d("Register", info)
        }
        btnCancel.setOnClickListener {
            setResult(200)
            finish()
        }
    }
}