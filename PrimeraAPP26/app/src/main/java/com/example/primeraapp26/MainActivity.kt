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
    val TAG="ciclovida"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "A: onCreate creado")
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)

        val activityContext = this
        val appContext = applicationContext



        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
            //CAMBIAR TEXTO BOTON
            //val boton = findViewById<Button>(R.id.miBoton)
            //boton.text="Pulsame chacho!"

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

       binding.buttonDialogoSistema?.setOnClickListener{

           requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 1001)

       }
        Log.d("DEMO2", "Activity: $activityContext")
        Log.d("DEMO2", "App: $appContext")
    }

    override fun onStart() {
        super.onStart()
        Log.d("DEMO", "A: onStart llamado")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "A: onResume llamado - ¡La Activity es visible y activa!")

    }

    override fun onPause() {
        super.onPause()
        Log.d("DEMO", "A: onPause llamado - Otra Activity toma el foco")

    }

    override fun onStop(){
        super.onStop()
        Log.d(TAG, "A: onStop llamado - La Activity ya no es visible")
    }

    override fun onRestart(){
        super.onRestart()
        Log.d(TAG, "A: onRestart llamado - Volviendo de estar 'stopped'")

    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "A: onDestroy llamado - La Activity está siendo destruida")

    }

}