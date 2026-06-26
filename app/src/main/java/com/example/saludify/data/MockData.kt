package com.example.saludify.data

import com.example.saludify.domain.model.Especialidad
import com.example.saludify.domain.model.ObraSocial
import com.example.saludify.domain.model.Turno
import com.example.saludify.domain.model.Usuario

object MockData {

    val usuarios = listOf(
        Usuario(
            id = 1,
            nombre = "María",
            apellido = "González",
            dni = "12345678",
            email = "maria.gonzalez@email.com",
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
        medico = "Dr. Roberto Silva",
        mes = "Jun",
        dia = "23",
        diaSemana = "Lun",
        hora = "10:30 hs",
        lugar = "Hospital Central"
    )

    val obraSocial = ObraSocial(
        id = 1,
        nombre = "Saludify",
        numeroAfiliado = "0000-000-1234"
    )

    var currentUser: Usuario? = null

    fun autenticar(dni: String, password: String): Usuario? =
        usuarios.find { it.dni == dni && it.password == password }
}
