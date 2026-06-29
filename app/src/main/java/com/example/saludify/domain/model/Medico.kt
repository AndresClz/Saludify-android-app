package com.example.saludify.domain.model

data class Medico(
    val id: Int,
    val nombre: String,
    val especialidad: String,
    val modalidad: String,   // "Presencial" | "Online"
    val lugar: String,
    val diaSemana: String,
    val dia: String,
    val mes: String,
    val hora: String
)