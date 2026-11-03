package com.example.boletin1unidad2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.boletin1unidad2.ui.theme.Boletin1Unidad2Theme
import com.example.boletin1unidad2.views.HeaderBanner
import com.example.boletin1unidad2.views.ProfileHeader
import com.example.boletin1unidad2.views.RegisterForm
import com.example.boletin1unidad2.views.UserAvatar
import com.example.boletin1unidad2.views.UsernameInput

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Boletin1Unidad2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {

                        Text(
                            text = "Ejercicio 1",
                            fontWeight = FontWeight.Bold
                        );

                        HeaderBanner(

                            title = "Primer ejercicio", modifier = Modifier.padding(innerPadding)

                        )

                        Text(
                            text = "Ejercicio 2",
                            fontWeight = FontWeight.Bold
                        );

                        UserAvatar(

                            resourceId = R.drawable.ic_launcher_background, modifier = Modifier.padding(innerPadding)

                        )
                        Text(
                            text = "Ejercicio 3",
                            fontWeight = FontWeight.Bold
                        );
                        ProfileHeader(

                            modifier = Modifier.padding(innerPadding)

                        )
                        Text(
                            text = "Ejercicio 4",
                            fontWeight = FontWeight.Bold
                        );
                        UsernameInput(

                            modifier = Modifier.padding(innerPadding)

                        );
                        Text(
                            text = "Ejercicio 5",
                            fontWeight = FontWeight.Bold
                        );
                        RegisterForm(

                            modifier = Modifier.padding(innerPadding)

                        );
                    }
                }
            }
        }
    }
}
