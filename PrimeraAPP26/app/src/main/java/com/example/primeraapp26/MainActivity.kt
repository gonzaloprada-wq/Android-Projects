package com.example.primeraapp26

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.primeraapp26.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("::tag","Estoy en onCreate")
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
            //CAMBIAR TEXTO BOTON
            //val boton = findViewById<Button>(R.id.miBoton)
            //boton.text="Pulsame chacho!"

        binding.miBoton.text="pulsame chacho v2"

        //Imprime un pop up
        binding.miBoton.setOnClickListener {

            val toast = Toast.makeText(
                applicationContext,
                "¡Me has pulsado chacho!",
                Toast.LENGTH_SHORT
            ).show()

            val intento= Intent(this, WelcomeActivity::class.java)
            startActivity(intento)

        }
        //EXPLICITO
        binding.buttonSend?.setOnClickListener {
            val mensaje = binding.editTextMessage?.text.toString()
            val intento = Intent(this, WelcomeActivity::class.java).apply{
                putExtra("MENSAJE_EXTRA", mensaje)
            }

            startActivity(intento)

        }
        //IMPLICITO
        binding.buttonOpenBrowser?.setOnClickListener{
            val webpage:Uri=Uri.parse("https://www.google.com")
            val intento=Intent(Intent.ACTION_VIEW, webpage)
            startActivity(intento)
        }

    }

    override fun onStop(){
        super.onStop()
        Log.d("::tag","Estoy en onStop")
        setContentView(R.layout.stop)
    }

}