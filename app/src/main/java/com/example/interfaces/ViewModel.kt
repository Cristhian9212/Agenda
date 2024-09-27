package com.example.interfaces

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class Contact(
    val nombres: String,
    val apellidos: String,
    val telefono: String,
    val direccion: String
)

class ContactViewModel : ViewModel() {
    val contactList = mutableStateListOf<Contact>()

    fun agregarContacto(contact: Contact) {
        contactList.add(contact)
    }

    fun eliminarContacto(contact: Contact) {
        contactList.remove(contact)
    }

    fun modificarContacto(oldContact: Contact, newContact: Contact) {
        val index = contactList.indexOf(oldContact)
        if (index != -1) {
            contactList[index] = newContact
        }
    }
}
