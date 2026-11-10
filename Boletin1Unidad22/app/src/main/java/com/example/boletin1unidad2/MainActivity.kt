package com.example.boletin1unidad2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.rememberNavController
import com.example.boletin1unidad2.ui.theme.Boletin1Unidad2Theme
import com.example.boletin1unidad2.views.ExperienceSlider
import com.example.boletin1unidad2.views.HeaderBanner
import com.example.boletin1unidad2.views.NotificationSettings
import com.example.boletin1unidad2.views.ProfileHeader
import com.example.boletin1unidad2.views.RegisterForm
import com.example.boletin1unidad2.views.RoleSelector
import com.example.boletin1unidad2.views.SubmitButton
import com.example.boletin1unidad2.views.TechInterests
import com.example.boletin1unidad2.views.UserAvatar
import com.example.boletin1unidad2.views.UsernameInput
import com.example.boletin1unidad2.views.editarAntiguos
import kotlin.jvm.java

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Boletin1Unidad2Theme {
                val context = LocalContext.current
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                    ) {

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
                        Text(
                            text = "Ejercicio 6",
                            fontWeight = FontWeight.Bold
                        );
                        RoleSelector(
                            modifier = Modifier.padding(innerPadding)
                        );
                        Text(
                            text = "Ejercicio 7",
                            fontWeight = FontWeight.Bold
                        );
                        TechInterests(
                            modifier = Modifier.padding(innerPadding)
                        );
                        Text(
                            text = "Ejercicio 8",
                            fontWeight = FontWeight.Bold
                        );

                        NotificationSettings(
                            modifier = Modifier.padding(innerPadding)
                        );

                        Text(
                            text = "Ejercicio 9",
                            fontWeight = FontWeight.Bold
                        );
                        ExperienceSlider(
                            modifier = Modifier.padding(innerPadding)

                        );
                        Text(
                            text = "Ejercicio 10",
                            fontWeight = FontWeight.Bold
                        );
                        editarAntiguos(
                            modifier = Modifier.padding(innerPadding)
                        )
                        Text(
                            text = "Ejercicio 11",
                            fontWeight = FontWeight.Bold
                        );
                        SubmitButton(
                            enabled = true,
                            onClick = {
                                Toast.makeText(this@MainActivity, "Perfil guardado correctamente", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@MainActivity, SegundaPantalla::class.java)

                                /*datos*/

                                startActivity(intent)

                            },
                            modifier = Modifier.padding(innerPadding)
                        )
                    }

                }
            }
        }
    }
}
