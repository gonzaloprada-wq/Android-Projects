package com.example.boletin1unidad2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.example.boletin1unidad2.ui.theme.Boletin1Unidad2Theme
import com.example.boletin1unidad2.views.DevConnectApp
import com.example.boletin1unidad2.views.DevDirectoryScreen

class SegundaPantalla : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val username = intent.getStringExtra("EXTRA_USERNAME") ?: ""
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: ""
        val role = intent.getStringExtra("EXTRA_ROLE") ?: ""
        val technologies = intent.getStringExtra("EXTRA_TECHNOLOGIES") ?: ""
        val experience = intent.getIntExtra("EXTRA_EXPERIENCE", 0)

        setContent {
            Boletin1Unidad2Theme {
                DevConnectApp(
                    onReset = { finish() }
                ) { innerPadding, _, _ ->
                    DevDirectoryScreen(
                        username = username,
                        email = email,
                        role = role,
                        technologies = technologies,
                        experience = experience,
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}
