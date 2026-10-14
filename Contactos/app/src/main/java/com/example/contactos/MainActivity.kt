package com.example.contactos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactos.databinding.ContactosBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val contactos : ContactosBinding = ContactosBinding.inflate(layoutInflater)
        setContentView(contactos.root)

        contactos.vistaContactos.layoutManager = LinearLayoutManager(this)
        contactos.vistaContactos.adapter = ContactosAdapter(
            listOf(
                // Puedes pasar una imagen específica como tercer parámetro, ej: Contacto("Juan", "611123456", R.drawable.tu_imagen)
                Contacto("Juan", "611123456"),
                Contacto("María", "678456123"),
                Contacto("Raúl", "644789456"),
                Contacto("Ana", "693882147")
                // Añade más
            )
        )

        ViewCompat.setOnApplyWindowInsetsListener(contactos.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
