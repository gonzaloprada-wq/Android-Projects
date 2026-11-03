package com.example.examen_gonzaloprada

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.examen_gonzaloprada.databinding.ActivityDictamenViewBinding
import com.example.examen_gonzaloprada.databinding.ActivityMainBinding

class DictamenView : AppCompatActivity() {

    fun verificarDatos(){

        val idPaquete = intent.getStringExtra("idPaquete")
        val peso = intent.getStringExtra("peso")
        val nombreRemitente = intent.getStringExtra("nombreRemitente")
        val paisRemitente = intent.getStringExtra("paisRemitente")
        val idSeleccionado = intent.getStringExtra("idSeleccionado")

        val listaDatos= listOf(idPaquete, peso, nombreRemitente, paisRemitente,idSeleccionado)

        if (listaDatos.contains("")){

            Toast.makeText(this, "Error! Ingresa todos los datos!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }



    }

    private lateinit var binding: ActivityDictamenViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDictamenViewBinding.inflate(layoutInflater)

        setContentView(binding.root)




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    //Aqui se ejecuta todo
    override fun onStart(){

        super.onStart()

        verificarDatos()

        procesarDatos();



    }
    //Esto procesa los datos y los meustra por pantalla
    fun procesarDatos(){

        val idPaquete = intent.getStringExtra("idPaquete")
        val peso = intent.getStringExtra("peso")
        val nombreRemitente = intent.getStringExtra("nombreRemitente")
        val paisRemitente = intent.getStringExtra("paisRemitente")
        val recepcionInput = intent.getStringExtra("recepcionInput")



        var tarifa = 0

        val pesoInt = peso?.toIntOrNull() ?: 0

        var costeBase=0

        if(recepcionInput=="Europa"){

            tarifa=10

            costeBase=10*pesoInt

        }else if(recepcionInput=="Internacional"){

            tarifa=18

            costeBase=18*pesoInt

        }else if(recepcionInput=="Nacional"){

            tarifa=5

            costeBase=5*pesoInt


        }else{

            tarifa=12
            costeBase=12*pesoInt

        }

        val tasaLogistica=(costeBase*0.15)/1.00

        val total=costeBase+tasaLogistica
        val totalString=total.toString().trim()

        binding.remitenteId.text=nombreRemitente
        binding.zonaId.text=paisRemitente
        binding.pesoId.text=peso
        binding.tarifaId.text=tarifa.toString()
        binding.costeId.text=costeBase.toString()
        binding.tasaId.text=tasaLogistica.toString()
        binding.totalId.text=total.toString()


        //Esto envia los datos a la vista
        binding.enviar.setOnClickListener{

            val intent = Intent(this, resumen::class.java)
            intent.putExtra("idPaquete",idPaquete )
            intent.putExtra("peso",peso )
            intent.putExtra("nombreRemitente", nombreRemitente)
            intent.putExtra("paisRemitente",paisRemitente )
            intent.putExtra("recepcionInput",recepcionInput )
            intent.putExtra("total",totalString)


                startActivity(intent)

            }

        }

    }


