package com.example.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit

@Composable
fun screenB(navController: NavController, contactViewModel: ContactViewModel = viewModel()) {
    // Definir el degradé de fondo
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFE3F2FD), Color(0xFFBBDEFB)) // Colores de degradé suave
    )

    // Estado para controlar qué contacto está siendo editado
    var contactToEdit by remember { mutableStateOf<Contact?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(gradientBrush), // Aplicar el degradé como fondo
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Título de la pantalla
        Text(
            text = "Contactos Guardados",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 32.dp) // Separar del borde superior
        )

        // Lista de contactos
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(contactViewModel.contactList.size) { index ->
                val contact = contactViewModel.contactList[index]
                ContactCard(
                    contact = contact,
                    onDelete = { contactViewModel.eliminarContacto(contact) },
                    onEdit = { contactToEdit = contact } // Actualiza el contacto a editar
                )
            }
        }

        // Mostrar diálogo de edición si hay un contacto seleccionado
        contactToEdit?.let { contact ->
            showEditContactDialog(
                contact = contact,
                onDismiss = { contactToEdit = null },
                onSave = { updatedContact ->
                    contactViewModel.modificarContacto(contact, updatedContact)
                    contactToEdit = null
                }
            )
        }

        // Botón redondo para volver a la pantalla A
        FloatingActionButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp),
            shape = CircleShape,
            containerColor = Color(0xFF6200EE)
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
        }
    }
}

@Composable
fun ContactCard(contact: Contact, onDelete: () -> Unit, onEdit: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono circular con inicial del nombre
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .padding(8.dp)
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = CircleShape,
                    color = Color(0xFFD3D3D3)
                ) {
                    Text(
                        text = contact.nombres.first().toString(),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .wrapContentSize(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = contact.nombres,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                if (expanded) {
                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Apellidos: ") }
                        append(contact.apellidos)
                    })
                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Teléfono: ") }
                        append(contact.telefono)
                    })
                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Dirección: ") }
                        append(contact.direccion)
                    })
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botones para editar y eliminar contacto
            IconButton(onClick = onEdit) {
                Icon(Icons.Filled.Edit, contentDescription = "Editar contacto", tint = Color(0xFF6200EE))
            }

            IconButton(onClick = onDelete) {
                Icon(Icons.Filled.Delete, contentDescription = "Eliminar contacto", tint = Color(0xFFE53935))
            }
        }
    }
}

@Composable
fun showEditContactDialog(
    contact: Contact,
    onDismiss: () -> Unit,
    onSave: (Contact) -> Unit
) {
    var nombres by remember { mutableStateOf(contact.nombres) }
    var apellidos by remember { mutableStateOf(contact.apellidos) }
    var telefono by remember { mutableStateOf(contact.telefono) }
    var direccion by remember { mutableStateOf(contact.direccion) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Editar Contacto") },
        text = {
            Column {
                OutlinedTextField(
                    value = nombres,
                    onValueChange = { nombres = it },
                    label = { Text("Nombres") }
                )
                OutlinedTextField(
                    value = apellidos,
                    onValueChange = { apellidos = it },
                    label = { Text("Apellidos") }
                )
                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono") }
                )
                OutlinedTextField(
                    value = direccion,
                    onValueChange = { direccion = it },
                    label = { Text("Dirección") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val updatedContact = Contact(nombres, apellidos, telefono, direccion)
                    onSave(updatedContact)
                }
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancelar")
            }
        }
    )
}
