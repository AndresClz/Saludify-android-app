package com.example.saludify.domain.model

data class Turno(
    val id: Int,
    val especialidad: String,
    val medico: String = "",
    val mes: String = "",
    val dia: String = "",
    val diaSemana: String = "",
    val hora: String = "",
    val lugar: String = ""
)
