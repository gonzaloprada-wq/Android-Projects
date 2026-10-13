package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityUserWelcomeBinding

class UserWelcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val binding = ActivityUserWelcomeBinding.inflate(layoutInflater)

        val nombreUsuario = intent.getStringExtra("nameUser")

        setContentView(binding.root)

        binding.buttonContinue.setOnClickListener {
            startActivity(Intent(this, MainMenuActivity::class.java).putExtra("nameUser", nombreUsuario))
        }

        binding.textoBienvenida.text = "Bienvenido: " + nombreUsuario

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
