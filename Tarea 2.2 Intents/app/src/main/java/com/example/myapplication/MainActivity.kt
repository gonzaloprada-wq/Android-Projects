package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameUser = "Usuario"
        val passwordUser = "1234"

        binding.buttonAccess.setOnClickListener {
            val user = binding.userName.text.toString().trim()
            val passwd = binding.passwordSplash.text.toString().trim()

            when {
                user.isEmpty() || passwd.isEmpty() ->
                    Toast.makeText(this, "Rellena todos los datos para continuar", Toast.LENGTH_SHORT).show()
                user == nameUser && passwd == passwordUser ->
                    startActivity(Intent(this, UserWelcome::class.java).putExtra("nameUser", user))
                else ->
                    startActivity(Intent(this, UserFailed::class.java))
            }
        }
    }
}