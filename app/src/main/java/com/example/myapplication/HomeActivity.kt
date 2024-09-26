package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.gson.Gson

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataRecorded = intent.getStringExtra("ACCOUNT")
        Log.d("HomeActivity", "Data recorded: $dataRecorded")
        if (dataRecorded != null) {
            val gson = Gson()
            val account = gson.fromJson(dataRecorded, AccountEntity::class.java)

            // Mostrar los datos del usuario en el log
            Log.d("HomeActivity", "Usuario logueado: ${account.username}, Nombre: ${account.firstName}")

            // Mostrar estos datos en el TextView con ID textView2
            binding.textView2.text = "Bienvenido, ${account.firstName} ${account.lastName}"
        } else {
            Log.d("HomeActivity", "No se recibieron datos del usuario")
        }
    }
}