package com.example.saludify.data

import com.example.saludify.domain.model.Especialidad
import com.example.saludify.domain.model.ObraSocial
import com.example.saludify.domain.model.Turno
import com.example.saludify.domain.model.Usuario

object MockData {

    val usuarios = listOf(
        Usuario(
            id = 1,
            nombre = "John",
            apellido = "Doe",
            dni = "12345678",
            email = "john.doe@email.com",
            password = "1234"
        )
    )

    val especialidades = listOf(
        Especialidad(id = 1, nombre = "Cardiología"),
        Especialidad(id = 2, nombre = "Clínica Médica"),
        Especialidad(id = 3, nombre = "Dermatología"),
        Especialidad(id = 4, nombre = "Traumatología"),
        Especialidad(id = 5, nombre = "Pediatría")
    )

    val proximoTurno = Turno(
        id = 1,
        especialidad = "Cardiología",
        fecha = "25/06/2026",
        hora = "15:00"
    )

    val obraSocial = ObraSocial(
        id = 1,
        nombre = "OSDE",
        numeroAfiliado = "123-456789/01"
    )

    var currentUser: Usuario? = null

    fun autenticar(dni: String, password: String): Usuario? =
        usuarios.find { it.dni == dni && it.password == password }
}
