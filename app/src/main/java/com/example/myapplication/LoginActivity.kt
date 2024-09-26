package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var gson : Gson
    var dataRecorded : String? = null
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
                intent.putExtra("ACCOUNT", dataRecorded )
                startActivity(intent)
            }else {
                Toast.makeText(applicationContext,"Cuenta no encontrada", Toast.LENGTH_SHORT).show()
                Log.d("Login failed", "Cuenta no encontrada")
            }
        }

        btnAddAccount.setOnClickListener {
            val intent = Intent(applicationContext, AccountActivity::class.java)
            activityResultLauncher.launch(intent)
        }

    }
    val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
        val resultCode = activityResult.resultCode
        if (resultCode == 100) {
            val data = activityResult.data
            dataRecorded = data?.getStringExtra("ACCOUNT_RECORD")
            gson = Gson()
            val account = gson.fromJson(dataRecorded, AccountEntity::class.java)

            val fistName = account.firstName
            Toast.makeText(applicationContext,"Nombre: $fistName", Toast.LENGTH_SHORT).show()

        } else if (resultCode == 200) {
            Toast.makeText(applicationContext,"Operación cancelada", Toast.LENGTH_SHORT).show()
        }

    }
    fun userExists(userName:String, password:String): Boolean {
        // TODO: leer cuenta.txt para ver si esta registrado
        return true
    }
}