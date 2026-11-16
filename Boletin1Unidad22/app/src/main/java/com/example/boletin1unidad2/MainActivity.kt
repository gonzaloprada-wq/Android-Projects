package com.example.boletin1unidad2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.launch
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
import com.example.boletin1unidad2.views.DevConnectApp
import com.example.boletin1unidad2.views.editarAntiguos
import kotlin.jvm.java

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Boletin1Unidad2Theme {
                val context = LocalContext.current
                var username by rememberSaveable { mutableStateOf("") }
                var email by rememberSaveable { mutableStateOf("") }
                var password by rememberSaveable { mutableStateOf("") }
                var selectedRole by rememberSaveable { mutableStateOf("Frontend") }
                var selectedInterests by rememberSaveable { mutableStateOf(setOf<String>()) }
                var sliderPosition by rememberSaveable { mutableStateOf(0f) }

                DevConnectApp(
                    onReset = {
                        username = ""
                        email = ""
                        password = ""
                        selectedRole = "Frontend"
                        selectedInterests = emptySet()
                        sliderPosition = 0f
                    }
                ) { innerPadding, snackbarHostState, scope ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                    ) {

                        Text(
                            text = "Ejercicio 1",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )

                        HeaderBanner(
                            title = "Primer ejercicio", modifier = Modifier.padding(10.dp)
                        )

                        Text(
                            text = "Ejercicio 2",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )

                        UserAvatar(
                            resourceId = R.drawable.ic_launcher_background, modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            text = "Ejercicio 3",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                        ProfileHeader(
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            text = "Ejercicio 4",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                        UsernameInput(
                            username = username,
                            onUsernameChanged = { username = it },
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            text = "Ejercicio 5",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                        RegisterForm(
                            email = email,
                            onEmailChanged = { email = it },
                            password = password,
                            onPasswordChanged = { password = it },
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            text = "Ejercicio 6",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                        RoleSelector(
                            selectedRole = selectedRole,
                            onRoleSelected = { selectedRole = it },
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            text = "Ejercicio 7",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                        TechInterests(
                            selectedInterests = selectedInterests,
                            onInterestChanged = { option, checked ->
                                selectedInterests = if (checked) {
                                    selectedInterests + option
                                } else {
                                    selectedInterests - option
                                }
                            },
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            text = "Ejercicio 8",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )

                        NotificationSettings(
                            modifier = Modifier.padding(10.dp)
                        )

                        Text(
                            text = "Ejercicio 9",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                        ExperienceSlider(
                            sliderPosition = sliderPosition,
                            onSliderChanged = { sliderPosition = it },
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            text = "Ejercicio 10",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                        editarAntiguos(
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            text = "Ejercicio 11",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                        SubmitButton(
                            enabled = true,
                            onClick = {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Perfil guardado correctamente")
                                }
                                val intent = Intent(this@MainActivity, SegundaPantalla::class.java).apply {
                                    putExtra("EXTRA_USERNAME", username)
                                    putExtra("EXTRA_EMAIL", email)
                                    putExtra("EXTRA_ROLE", selectedRole)
                                    putExtra("EXTRA_TECHNOLOGIES", selectedInterests.joinToString(", "))
                                    putExtra("EXTRA_EXPERIENCE", sliderPosition.toInt())
                                }
                                startActivity(intent)
                            },
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                }
            }
        }
    }
}
