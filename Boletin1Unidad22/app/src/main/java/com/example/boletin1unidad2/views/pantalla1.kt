package com.example.boletin1unidad2.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boletin1unidad2.R
import com.example.boletin1unidad2.ui.theme.Blue26
import com.example.boletin1unidad2.ui.theme.Boletin1Unidad2Theme


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
            .padding(16.dp)


    ) {

        UserAvatar(R.drawable.ic_launcher_background)

        Column(

            modifier = modifier.padding(start = 8.dp)

        ) {

            HeaderBanner("Gonzalo Prada")
            Text(

                text = "Desarrollador Senior"

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
    Row() {

        Column() {
            var username by remember {

                mutableStateOf("")

            }

            TextField(

                value = username,
                onValueChange = { nuevoUser ->
                    username = nuevoUser
                },
                label = { Text("Ingresa tu nombre de usuario") }
            )

            Text(

                modifier = modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Bold,
                text = "Nombre de usuario: $username",


                );
        }


    }
}

/*Ejercicio 5*/
@Composable
fun RegisterForm(modifier: Modifier = Modifier) {

    var email by remember {

        mutableStateOf("")

    }



    OutlinedTextField(

        value = email,
        onValueChange = { nuevoEmail ->
            email = nuevoEmail
        },

        label = { Text("Ingresa tu email") }

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