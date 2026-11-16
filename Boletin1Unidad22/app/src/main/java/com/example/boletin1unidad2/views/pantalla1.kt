package com.example.boletin1unidad2.views

import android.widget.Toast
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.IconButton
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.TopAppBar
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
fun UsernameInput(username: String, onUsernameChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        TextField(value = username, onValueChange = onUsernameChanged, label = { Text("Ingresa tu nombre de usuario") })

        Text(
            text = "Nombre de usuario: $username",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, start = 16.dp)
        )
    }
}

/*Ejercicio 5*/
@Composable
fun RegisterForm(
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = email, onValueChange = onEmailChanged, label = { Text("Ingresa tu email") }, modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChanged,
            label = { Text("Ingresa tu contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }
}

/*Ejercicio 6*/
@Composable
fun RoleSelector(
    selectedRole: String,
    onRoleSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val roles = listOf("Frontend", "Backend", "Mobile", "Fullstack")

    Column(modifier = modifier) {
        roles.forEach { role ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),

                ) {
                RadioButton(
                    selected = (role == selectedRole), onClick = { onRoleSelected(role) })
                Text(
                    text = role, modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

/*Ejercicio 7*/
@Composable
fun TechInterests(
    selectedInterests: Set<String>,
    onInterestChanged: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val opciones = listOf("Kotlin", "Jetpack Compose", "Corrutinas")

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
                        onInterestChanged(opcion, checked)
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
fun ExperienceSlider(
    sliderPosition: Float,
    onSliderChanged: (Float) -> Unit,
    modifier: Modifier = Modifier
) {

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
                value = sliderPosition, onValueChange = onSliderChanged,

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
fun ProfileSummaryCard(
    username: String,
    email: String,
    role: String,
    technologies: String,
    experience: Int,
    modifier: Modifier = Modifier
) {
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
                HeaderBanner(if (username.isNotEmpty()) username else "Nombre del usuario")
                Text(
                    text = if (email.isNotEmpty()) email else "Gmail correo electronico", modifier = Modifier.padding(start = 12.dp)
                )
                Text(
                    text = if (role.isNotEmpty()) role else "Trabajo", modifier = Modifier.padding(start = 12.dp)
                )
                Text(
                    text = if (technologies.isNotEmpty()) "Tecnologías: $technologies" else "Tecnologías (lista)", modifier = Modifier.padding(start = 12.dp)
                )
                Text(
                    text = "Años experiencia: $experience", modifier = Modifier.padding(start = 12.dp, end = 10.dp)
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
//Ejercicio 13 y 14
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevConnectApp(
    modifier: Modifier = Modifier,
    onReset: () -> Unit = {},
    content: @Composable (PaddingValues, SnackbarHostState, CoroutineScope) -> Unit = { _, _, _ -> }
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "DevConnectApp",
                        fontWeight = FontWeight.Bold,
                        color = Blue26
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true }
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refrescar / Limpiar"
                )
            }
        }
    ) { innerPadding ->
        content(innerPadding, snackbarHostState, scope)

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "¿Reiniciar formulario?") },
                text = { Text(text = "Se perderán todos los datos introducidos.") },
                confirmButton = {
                    Button(
                        onClick = {
                            onReset()
                            showDialog = false
                        }
                    ) {
                        Text("Sí")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text("No")
                    }
                }
            )
        }
    }
}

data class DevProfile(
    val id: Int,
    val name: String,
    val role: String,
    val technologies: String = "",
    val experience: Int = 0,
    val avatarUrl: String = ""
)

val listaDevProfiles = listOf(
    DevProfile(1, "Ana Gómez", "Frontend Developer", "Kotlin, Compose, React", 3, "https://i.pravatar.cc/150?img=1"),
    DevProfile(2, "Carlos Pérez", "Backend Developer", "Java, Spring Boot, SQL", 5, "https://i.pravatar.cc/150?img=2"),
    DevProfile(3, "María López", "Fullstack Developer", "Kotlin, Node.js, TypeScript", 4, "https://i.pravatar.cc/150?img=3"),
    DevProfile(4, "Luis Martínez", "Android Developer", "Kotlin, Jetpack Compose, Coroutines", 6, "https://i.pravatar.cc/150?img=4"),
    DevProfile(5, "Elena Rodríguez", "UI/UX Designer", "Figma, Material Design", 2, "https://i.pravatar.cc/150?img=5"),
    DevProfile(6, "David Sánchez", "DevOps Engineer", "Docker, Kubernetes, AWS", 7, "https://i.pravatar.cc/150?img=6"),
    DevProfile(7, "Sofía Torres", "iOS Developer", "Swift, SwiftUI", 3, "https://i.pravatar.cc/150?img=7"),
    DevProfile(8, "Javier Ruiz", "Data Scientist", "Python, Pandas, Machine Learning", 4, "https://i.pravatar.cc/150?img=8"),
    DevProfile(9, "Lucía Fernández", "QA Engineer", "JUnit, Espresso, Cypress", 3, "https://i.pravatar.cc/150?img=9"),
    DevProfile(10, "Pedro Gómez", "Backend Developer", "Go, PostgreSQL, Redis", 5, "https://i.pravatar.cc/150?img=10")
)

@Composable
fun DevDirectoryScreen(
    username: String = "",
    email: String = "",
    role: String = "",
    technologies: String = "",
    experience: Int = 0,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredDevelopers = listaDevProfiles.filter { profile ->
        profile.name.contains(searchQuery, ignoreCase = true) ||
        profile.role.contains(searchQuery, ignoreCase = true)
    }

    LazyColumn(
        modifier = modifier
    ) {
        item {
            Text(
                text = "Ejercicio 15 y 17",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )
        }

        item {
            Text(
                text = "Resumen de tu Perfil",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(10.dp)
            )
            ProfileSummaryCard(
                username = username,
                email = email,
                role = role,
                technologies = technologies,
                experience = experience
            )

            Text(
                text = "Directorio de Desarrolladores",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(10.dp)
            )
        }

        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar por nombre o rol...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                singleLine = true
            )
        }

        items(filteredDevelopers) { profile ->
            var isLiked by rememberSaveable { mutableStateOf(false) }
            var likesCount by rememberSaveable { mutableStateOf(0) }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CardColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = profile.avatarUrl.ifEmpty { "https://i.pravatar.cc/150" },
                        contentDescription = "Avatar de ${profile.name}",
                        placeholder = painterResource(id = R.drawable.ic_launcher_background),
                        error = painterResource(id = R.drawable.ic_launcher_background),
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = profile.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Blue26,
                            modifier = Modifier.padding(start = 12.dp, top = 4.dp)
                        )
                        Text(
                            text = "Rol: ${profile.role}",
                            modifier = Modifier.padding(start = 12.dp)
                        )
                        if (profile.technologies.isNotEmpty()) {
                            Text(
                                text = "Tecnologías: ${profile.technologies}",
                                modifier = Modifier.padding(start = 12.dp)
                            )
                        }
                        Text(
                            text = "Años experiencia: ${profile.experience}",
                            modifier = Modifier.padding(start = 12.dp, end = 10.dp)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                isLiked = !isLiked
                                likesCount = if (isLiked) likesCount + 1 else likesCount - 1
                            }
                        ) {
                            Icon(
                                imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Me gusta",
                                tint = if (isLiked) Color.Red else Color.Gray
                            )
                        }

                        Text(
                            text = "$likesCount",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

