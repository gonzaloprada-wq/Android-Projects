package com.example.boletin1unidad2.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boletin1unidad2.R
import com.example.boletin1unidad2.ui.theme.Blue26
import com.example.boletin1unidad2.ui.theme.Boletin1Unidad2Theme
import com.example.boletin1unidad2.ui.theme.CardColor


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Boletin1Unidad2Theme {
        Greeting("Android")
    }
}

/*EJERCICIO 1*/
@Composable
fun HeaderBanner(title: String, modifier: Modifier = Modifier) {
    Text(
        text = "$title",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Blue26,
        modifier = modifier.padding(start = 12.dp, top = 12.dp)
    )
}

//@Preview(showBackground = true)
@Composable
fun HeaderBannerPreview() {
    Boletin1Unidad2Theme {
        HeaderBanner(
            "Primer Ejercicio", modifier = Modifier.fillMaxWidth()
        )
    }
}

/*EJERCICIO 2*/
@Composable
fun UserAvatar(resourceId: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = resourceId),
        contentDescription = "Imagen de avatar",
        modifier = modifier
            .size(100.dp)
            .padding(8.dp)
            .clip(CircleShape)
    )
}

//@Preview(showBackground = true)
@Composable
fun UserAvatarPreview() {
    Boletin1Unidad2Theme {
        UserAvatar(
            R.drawable.ic_launcher_background, modifier = Modifier.fillMaxWidth()
        )
    }
}

/*EJERCICIO 3*/
@Composable
fun ProfileHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        UserAvatar(R.drawable.ic_launcher_background)
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            HeaderBanner("Gonzalo Prada")
            Text(
                text = "Desarrollador Senior", modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun ProfileHeaderPreview() {
    Boletin1Unidad2Theme {
        ProfileHeader()
    }
}

/*Ejercicio 4*/
@Composable
fun UsernameInput(modifier: Modifier = Modifier) {
    var username by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier) {
        TextField(value = username, onValueChange = { nuevoUser ->
            username = nuevoUser
        }, label = { Text("Ingresa tu nombre de usuario") })

        Text(
            text = "Nombre de usuario: $username",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, start = 16.dp)
        )
    }
}

/*Ejercicio 5*/
@Composable
fun RegisterForm(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        var email by rememberSaveable  { mutableStateOf("") }

        OutlinedTextField(
            value = email, onValueChange = { nuevoEmail ->
            email = nuevoEmail
            }, label = { Text("Ingresa tu email") }, modifier = Modifier.padding(bottom = 8.dp)
        )

        var contraseña by remember { mutableStateOf("") }

        OutlinedTextField(
            value = contraseña,
            onValueChange = { nuevoTexto ->
                contraseña = nuevoTexto
            },
            label = { Text("Ingresa tu contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }
}

/*Ejercicio 6*/
@Composable
fun RoleSelector(modifier: Modifier = Modifier) {

    val roles = listOf("Frontend", "Backend", "Mobile", "Fullstack")
    var selectedRole by remember { mutableStateOf(roles[0]) }

    Column(modifier = modifier) {
        roles.forEach { role ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),

                ) {
                RadioButton(
                    selected = (role == selectedRole), onClick = { selectedRole = role })
                Text(
                    text = role, modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

/*Ejercicio 7*/
@Composable
fun TechInterests(modifier: Modifier = Modifier) {
    val opciones = listOf("Kotlin", "Jetpack Compose", "Corrutinas")
    var selectedInterests by remember { mutableStateOf(setOf<String>()) }

    Column(modifier = modifier) {
        opciones.forEach { opcion ->
            val isChecked = selectedInterests.contains(opcion)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { checked ->
                        selectedInterests = if (checked) {

                            selectedInterests + opcion

                        } else {
                            selectedInterests - opcion
                        }
                    }
                )
                Text(
                    text = opcion,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
/*Ejercicio 8*/

@Composable
fun NotificationSettings(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var estado by rememberSaveable  { mutableStateOf(false) }

    val mensaje = "Recibir novedades del sector:  "

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = mensaje,
                modifier = Modifier.padding(start = 8.dp)
            )

            Switch(
                checked = estado,
                onCheckedChange = { isChecked ->

                    estado = isChecked

                    if (isChecked) {

                        Toast.makeText(context, "Notificaciones: Activas", Toast.LENGTH_SHORT).show()

                    } else {

                        Toast.makeText(context, "Notificaciones: Inactivas", Toast.LENGTH_SHORT).show()


                    }
                }
            )
        }
    }
}
/*Ejercicio 9*/

@Composable
fun ExperienceSlider(modifier: Modifier = Modifier) {

    var sliderPosition by rememberSaveable  { mutableStateOf(0f) }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Años de experiencia: ${sliderPosition.toInt()}",
                fontWeight = FontWeight.Bold
            )
            Slider(
                value = sliderPosition, onValueChange = { sliderPosition = it },

                valueRange = 0f..10f,

                steps = 9,

                )


        }
    }

}
/*Ejercicio 10*/

@Composable
fun editarAntiguos(modifier: Modifier = Modifier){

    Text(
        text = "Ejercicio Realizado",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Red,
        modifier = modifier.padding(start = 12.dp, top = 12.dp)
    )

}

/*Ejercicio 11*/

@Composable
fun SubmitButton(enabled: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {

    val context = LocalContext.current
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp),){

        Button(
            onClick = {

                onClick()

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text("Enviar")
        }


    }

}

/*Ejercicio 12*/
@Composable
fun ProfileSummaryCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserAvatar(R.drawable.ic_launcher_background)

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                HeaderBanner("Nombre del usuario")
                Text(
                    text = "Gmail correo electronico", modifier = Modifier.padding(start = 12.dp)
                )
                Text(
                    text = "Trabajo", modifier = Modifier.padding(start = 12.dp)
                )
                Text(
                    text = "Tecnologias (lista)", modifier = Modifier.padding(start = 12.dp)
                )
                Text(
                    text = "Años experiencia ", modifier = Modifier.padding(start = 12.dp, end = 10.dp)
                )
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun ProfileSummaryCardPreview() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        ProfileSummaryCard(modifier = Modifier.padding(innerPadding))
    }
}*/