package com.example.contactos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_info)

        // Recuperar el objeto Contacto enviado desde MainActivity
        val contacto = @Suppress("DEPRECATION") intent.getSerializableExtra("CONTACTO") as? Contacto

        val tvNombre: TextView = findViewById(R.id.tvNombreInfo)
        val tvTelefono: TextView = findViewById(R.id.tvTelefonoInfo)
        val tvCorreo: TextView = findViewById(R.id.tvCorreoInfo)
        val botonEnviarCorreo: Button = findViewById(R.id.btnEnviarCorreo)

        contacto?.let { contacto ->

            tvNombre.text = "Nombre: ${contacto.nombre}"
            tvTelefono.text = "Teléfono: ${contacto.telefono}"
            tvCorreo.text = "Correo: ${contacto.correo}"


            botonEnviarCorreo.setOnClickListener {
                Toast.makeText(this, "Enviando correo a ${contacto.nombre}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${contacto.correo}")))
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, id ->
            val systemBars = id.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            id
        }
    }
}
