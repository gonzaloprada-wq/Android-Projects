package com.example.contactos
data class Contacto(val nombre: String, val telefono: String) {


    val imagen: Int
        get() {

            val ultimoDigito = telefono.filter { it.isDigit() }.lastOrNull()?.digitToInt() ?: 0

            return if (ultimoDigito % 2 == 0) {

                R.drawable.ic_launcher_foreground

            } else {

                R.drawable.ic_launcher_background
            }
        }


}