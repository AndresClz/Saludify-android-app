package com.example.saludify.data

import com.example.saludify.domain.model.Especialidad
import com.example.saludify.domain.model.Medico
import com.example.saludify.domain.model.MedicoCartilla
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

    val medicos = listOf(
        Medico(id = 1, nombre = "Dr. Roberto Silva",  especialidad = "Cardiología", modalidad = "Presencial", lugar = "Hospital Central",  direccion = "Av. Santa Fe 1234", diaSemana = "Lun", dia = "23", mes = "Jun", hora = "10:30 hs"),
        Medico(id = 2, nombre = "Dra. Ana Martínez",  especialidad = "Cardiología", modalidad = "Presencial", lugar = "Sanatorio Güemes",  direccion = "Córdoba 3456",      diaSemana = "Mar", dia = "24", mes = "Jun", hora = "9:00 hs"),
        Medico(id = 3, nombre = "Dr. Carlos López",   especialidad = "Cardiología", modalidad = "Online",     lugar = "Videoconsulta",                                      diaSemana = "Lun", dia = "23", mes = "Jun", hora = "11:00 hs")
    )

    val medicosCartilla = listOf(
        MedicoCartilla(
            id = 1,
            nombre = "Dr. Héctor Fernández",
            especialidad = "Cardiología",
            lugar = "Consultorio propio",
            direccion = "Av. Callao 892, piso 3, of. B",
            horario = "Lun–Vie 14:00–18:00",
            abierto = true,
            statusLabel = "Abierto",
            iniciales = "HF",
            avatarToken = "brand"
        ),
        MedicoCartilla(
            id = 2,
            nombre = "Dra. Marta Rodríguez",
            especialidad = "Cardiología · Electrofisiología",
            lugar = "Clínica del Sol",
            direccion = "Scalabrini Ortiz 2540",
            horario = "Mar y Jue 9:00–13:00",
            abierto = false,
            statusLabel = "Cerrado · abre mañana",
            iniciales = "MR",
            avatarToken = "purple"
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
