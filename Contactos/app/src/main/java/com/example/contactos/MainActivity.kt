package com.example.contactos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerContactos.layoutManager = LinearLayoutManager(this)
        binding.recyclerContactos.adapter = ContactosAdapter(
            listOf(
                Contacto("Juan", "611123456", correo = "ejemploDeCorreo@343.com"),
                Contacto("María", "678456123", correo = "ejemploDeCorreo@343.com"),
                Contacto("Raúl", "644789456", correo = "ejemploDeCorreo@343.com"),
                Contacto("Ana", "69388213447", correo = "ejemploDeCorreo@343.com"),
                Contacto("Juan2", "6111232456", correo = "ejemploDeCorreo@343.com"),
                Contacto("María2", "67845614223", correo = "ejemploDeCorreo@343.com"),
                Contacto("Raúl2", "6447893456", correo = "ejemploDeCorreo@343.com"),
                Contacto("Ana2", "6938821475", correo = "ejemploDeCorreo@343.com")
            ),
            onInfoClicked = { contacto ->
                val intent = Intent(this, InfoActivity::class.java).apply {
                    putExtra("CONTACTO", contacto)
                }
                startActivity(intent)
            },
            onCallClicked = { contacto ->
                Toast.makeText(this, "Llamando a ${contacto.nombre}", Toast.LENGTH_SHORT).show()
                val dial = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:" + contacto.telefono)
                )
                startActivity(dial)
            }
        )

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
