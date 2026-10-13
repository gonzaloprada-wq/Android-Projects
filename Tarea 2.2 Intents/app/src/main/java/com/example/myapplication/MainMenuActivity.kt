package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val intent= Intent(Intent.ACTION_VIEW)
        val binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombreUsuario = intent.getStringExtra("nameUser")

        binding.textoBienvenida2.text = "Bienvenido: " + nombreUsuario



        binding.buttonNavegador.setOnClickListener {
            val ingresado = binding.ingresado.text.toString().trim()
            when {

                ingresado.isEmpty() ->{

                    Toast.makeText(this, "Rellana todo los campos", Toast.LENGTH_SHORT).show()

                }else -> {


                val ingresado = binding.ingresado.text.toString().trim()
                intent.data="https://www.google.com/search?q=${ingresado}".toUri()
                startActivity(intent)


            }


            }}

        binding.buttonTelefono.setOnClickListener {
            val ingresado = binding.ingresado.text.toString().trim()
            when {

                ingresado.isEmpty() ->{

                    Toast.makeText(this, "Rellana todo los campos", Toast.LENGTH_SHORT).show()

                }else -> {

                    val intent=  Intent(Intent.ACTION_DIAL)
                    intent.data=("tel:"+ingresado).toUri()
                    startActivity(intent)
            }


            }}


        binding.buttonMensaje.setOnClickListener {
            val ingresado = binding.ingresado.text.toString().trim()
            when {

                ingresado.isEmpty() ->{

                    Toast.makeText(this, "Rellana todo los campos", Toast.LENGTH_SHORT).show()

                }else -> {

                val smsIntent = Intent(Intent.ACTION_VIEW)
                smsIntent.data = Uri.parse("smsto:" + ingresado) // número escrito en el EditText
                smsIntent.putExtra("sms_body", "Hola desde mi app") // texto opcional
                startActivity(smsIntent)
            }


            }}

        binding.buttonCompartir.setOnClickListener {
            val ingresado = binding.ingresado.text.toString().trim()
            when {

                ingresado.isEmpty() ->{

                    Toast.makeText(this, "Rellana todo los campos", Toast.LENGTH_SHORT).show()

                }else -> {


                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, ingresado) // texto escrito en el EditText
                startActivity(Intent.createChooser(shareIntent, "Compartir con..."))


            }


            }}

        binding.Volver.setOnClickListener {

            finish()

        }


        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets



        }


    }




}
