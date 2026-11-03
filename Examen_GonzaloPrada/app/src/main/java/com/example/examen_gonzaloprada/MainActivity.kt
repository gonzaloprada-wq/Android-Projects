package com.example.examen_gonzaloprada

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.examen_gonzaloprada.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


       binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        enviarDatos()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun enviarDatos(){


        binding.enviarDatosMain.setOnClickListener{

            val intent = Intent(this, DictamenView::class.java)
            intent.putExtra("idPaquete", binding.codigoInput.text.toString().trim())
            intent.putExtra("peso", binding.pesoInput.text.toString().trim())
            intent.putExtra("nombreRemitente", binding.nombreInput.text.toString().trim())
            intent.putExtra("paisRemitente", binding.paisInput.text.toString().trim())
            intent.putExtra("recepcionInput", binding.recepcionInput.text.toString().trim())


            startActivity(intent)

        }


    }



}