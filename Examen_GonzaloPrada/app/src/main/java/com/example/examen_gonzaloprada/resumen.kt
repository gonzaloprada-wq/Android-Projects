package com.example.examen_gonzaloprada

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.example.examen_gonzaloprada.databinding.ActivityResumenBinding

class resumen : AppCompatActivity() {

    private lateinit var binding: ActivityResumenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityResumenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        procesarDatos()

        //esto procesa los datos recibiendolos y enviandolos a la vista
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    //esto procesa los datos recibiendolos y enviandolos a la vista
    fun procesarDatos(){

        val peso = intent.getStringExtra("peso")
        val nombreRemitente = intent.getStringExtra("nombreRemitente")
        val paisRemitente = intent.getStringExtra("paisRemitente")
        val total = intent.getStringExtra("total")


        binding.remitenteId2.text=nombreRemitente
        binding.paisId2.text=paisRemitente
        binding.pesoId2.text=peso
        binding.totalId2.text=total


        //Esto envia los datos por correo
        binding.enviar2.setOnClickListener{

            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {

                data = Uri.parse("mailto:${nombreRemitente}")

                putExtra(Intent.EXTRA_SUBJECT, "Confirmacion")

                putExtra(Intent.EXTRA_TEXT, "Pais remitente: $paisRemitente\nPeso: $peso\nTotal: $total")
            }

            startActivity(emailIntent)

        }

    }
}