package com.example.contactos

import java.io.Serializable

data class Contacto(val nombre: String, val telefono: String, val correo: String) : Serializable {

    val imagen: Int
        get() {
            val ultimoDigito = telefono.filter { it.isDigit() }.lastOrNull()?.digitToInt() ?: 0

            return when (ultimoDigito) {
                0 -> R.drawable._0
                1 -> R.drawable._1
                2 -> R.drawable._2
                3 -> R.drawable._3
                4 -> R.drawable._4
                5 -> R.drawable._5
                6 -> R.drawable._6
                7 -> R.drawable._7
                8 -> R.drawable._8
                9 -> R.drawable._9
                else -> R.drawable._0
            }
        }
}
