package com.example.boletin1unidad2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.boletin1unidad2.ui.theme.Boletin1Unidad2Theme
import com.example.boletin1unidad2.views.ProfileSummaryCard

class SegundaPantalla : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Boletin1Unidad2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                    ) {

                        Text(
                            text = "Ejercicio 12",
                            fontWeight = FontWeight.Bold
                        );
                        ProfileSummaryCard(
                            modifier = Modifier.padding(innerPadding)

                        )

                    }
                }
            }
        }
    }
}
