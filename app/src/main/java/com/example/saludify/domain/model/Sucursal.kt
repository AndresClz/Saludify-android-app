package com.example.saludify.domain.model

data class Sucursal(
    val nombre: String,
    val direccion: String,
    val ciudad: String,
    val horario: String,
    val telefono: String,
    val distanciaTexto: String,
    val tiempoEstimado: String,
    val estaAbierto: Boolean,
    val statusLabel: String,
    val horarioApertura: String?,
    val esMasCercana: Boolean
)
