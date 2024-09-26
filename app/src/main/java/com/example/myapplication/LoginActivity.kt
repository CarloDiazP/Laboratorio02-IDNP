package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var gson: Gson
    var dataRecorded: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gson = Gson()

        val btnLogin = binding.btnLogin
        val btnAddAccount = binding.btnAddAccount

        btnLogin.setOnClickListener {
            val userName = binding.userName.text.toString()
            val password = binding.password.text.toString()
            // Leer txt, transformarlo en json y verificar si el usuario existe
            if (userExists(userName, password)) {
                val intent = Intent(applicationContext, HomeActivity::class.java)
                Log.d("datarecordeddwqeqwed", "data : $dataRecorded")

                intent.putExtra("ACCOUNT", dataRecorded)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Cuenta no encontrada", Toast.LENGTH_SHORT).show()
                Log.d("Login failed", "Cuenta no encontrada")
            }
        }

        btnAddAccount.setOnClickListener {
            val intent = Intent(applicationContext, AccountActivity::class.java)
            activityResultLauncher.launch(intent)
        }
    }

    private val activityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        val resultCode = activityResult.resultCode
        if (resultCode == 100) {
            val data = activityResult.data
            dataRecorded = data?.getStringExtra("ACCOUNT_RECORD")
            val account = gson.fromJson(dataRecorded, AccountEntity::class.java)

            // Guardar los datos en el archivo txt
            guardarUsuario(applicationContext, account)

            val firstName = account.firstName
            Toast.makeText(applicationContext, "Nombre: $firstName guardado", Toast.LENGTH_SHORT).show()

        } else if (resultCode == 200) {
            Toast.makeText(applicationContext, "Operación cancelada", Toast.LENGTH_SHORT).show()
        }
    }

    // Función para guardar el usuario en un archivo txt
    private fun guardarUsuario(context: Context, usuario: AccountEntity) {
        val archivo = File(context.filesDir, "cuentas.txt")

        // Leer el archivo si ya existe
        val usuarios: MutableList<AccountEntity> = if (archivo.exists()) {
            val reader = FileReader(archivo)
            gson.fromJson(reader, Array<AccountEntity>::class.java).toMutableList().also { reader.close() }
        } else {
            mutableListOf()
        }

        // Agregar el nuevo usuario
        usuarios.add(usuario)

        // Escribir la lista actualizada de usuarios en el archivo
        val writer = FileWriter(archivo)
        writer.write(gson.toJson(usuarios))
        writer.close()
    }

    // Función para verificar si el usuario existe en el archivo txt
    private fun userExists(userName: String, password: String): Boolean {
        val archivo = File(applicationContext.filesDir, "cuentas.txt")

        if (!archivo.exists()) {
            return false
        }

        val reader = FileReader(archivo)
        val usuarios: Array<AccountEntity> = gson.fromJson(reader, Array<AccountEntity>::class.java)
        reader.close()

        // Verificar si el usuario y contraseña coinciden
        usuarios.forEach { usuario ->
            if (usuario.username == userName && usuario.password == password) {
                // Si se encuentra el usuario, asignar la data a dataRecorded
                dataRecorded = gson.toJson(usuario) // Asignar el JSON del usuario encontrado
                return true // Salir de la función
            }
        }

        return false // Retornar false si no se encuentra el usuario
    }
}
