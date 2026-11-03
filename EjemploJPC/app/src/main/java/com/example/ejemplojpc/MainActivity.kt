package com.example.ejemplojpc

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejemplojpc.ui.theme.EjemploJPCTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjemploJPCTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                    topBar = {Text("Soy la barra superior")},

                    bottomBar = {Text("Soy la barra inferior")},

                    floatingActionButton = {

                        Button(onClick = { }) {
                            Text("Soy el boton flotante")
                        }

                    },

                ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)


                    )


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val contexto = LocalContext.current
    var texto by remember { mutableStateOf("Ejemplo") }
    Column(
        modifier = modifier.padding(20.dp)
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier.background(Color.Green)
        )
        Image(painterResource(R.drawable.ic_launcher_foreground), contentDescription = "Prueba")

        Button(
            onClick = {
                Toast.makeText(contexto, "Hola", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Pulsame")
        }

        OutlinedTextField(
            modifier = modifier.padding(3.dp),
            label = { Text("Introduce algo") },
            value = texto,
            onValueChange = {

                texto=it

            }
        )
        Row(
            modifier = Modifier.background(Color.Green).fillMaxWidth().height(100.dp),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {

            Column() {

                Text(text="Hola 1 ")
                Text(text="Hola 2 ")

            }

            Column() {

                Text(text="Mundo 3 ")
                Text(text="Mundo 4 ")

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EjemploJPCTheme {
        Greeting("Android")
    }
}