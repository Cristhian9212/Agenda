package com.example.interfaces

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.interfaces.Contact
import com.example.interfaces.ContactViewModel
import com.example.interfaces.R

@Composable
fun ScreenA(navController: NavController, contactViewModel: ContactViewModel = viewModel()) {
    var nombres by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFDE3E9), Color(0xFFBBDEFB)),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(gradientBrush),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp)) // Incrementa el espaciado para bajar la imagen

        Surface(
            modifier = Modifier.size(100.dp),
            shape = CircleShape,
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Image(
                painter = painterResource(id = R.drawable.prueba),
                contentDescription = "Imagen de Contacto",
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(32.dp)) // Bajar los campos de texto

        TextField(
            value = nombres,
            onValueChange = { nombres = it },
            label = { Text("Nombres") },
            modifier = Modifier
                .width(280.dp)
                .padding(vertical = 8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFBBDEFB),
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        TextField(
            value = apellidos,
            onValueChange = { apellidos = it },
            label = { Text("Apellidos") },
            modifier = Modifier
                .width(280.dp)
                .padding(vertical = 8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFBBDEFB),
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        TextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            modifier = Modifier
                .width(280.dp)
                .padding(vertical = 8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFBBDEFB),
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Dirección") },
            modifier = Modifier
                .width(280.dp)
                .padding(vertical = 8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFBBDEFB),
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }

        Spacer(modifier = Modifier.weight(1f))

        // Colocamos los botones al final de la pantalla, alineados como en screenB
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botón redondo para guardar los datos
            FloatingActionButton(
                onClick = {
                    if (nombres.isBlank() || apellidos.isBlank() || telefono.isBlank() || direccion.isBlank()) {
                        errorMessage = "Por favor, llenar los campos solicitados."
                    } else {
                        errorMessage = ""
                        val newContact = Contact(nombres, apellidos, telefono, direccion)
                        contactViewModel.agregarContacto(newContact)
                        navController.navigate("screen_b")
                    }
                },
                modifier = Modifier.size(60.dp), // Tamaño similar al de screenB
                shape = CircleShape,
                containerColor = Color(0xFFFAFAFA)
            ) {
                Text(text = "Guardar", color = Color.Black)
            }

            // Botón redondo para ir a la agenda
            FloatingActionButton(
                onClick = {
                    navController.navigate("screen_b")
                },
                modifier = Modifier.size(60.dp), // Tamaño similar al de screenB
                shape = CircleShape,
                containerColor = Color(0xFFF9F7FD)
            ) {
                Text(text = "Agenda", color = Color.Black)
            }
        }
    }
}
