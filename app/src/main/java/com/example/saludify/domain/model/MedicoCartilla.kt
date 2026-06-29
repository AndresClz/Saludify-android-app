package com.example.saludify.domain.model

data class MedicoCartilla(
    val id: Int,
    val nombre: String,
    val especialidad: String,
    val lugar: String,
    val direccion: String,
    val horario: String,
    val abierto: Boolean,
    val statusLabel: String,
    val iniciales: String,
    val avatarToken: String  // "brand" | "purple"
)
