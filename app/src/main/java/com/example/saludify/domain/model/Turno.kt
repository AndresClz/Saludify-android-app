package com.example.saludify.domain.model

data class Turno(
    val id: Int,
    val especialidad: String,
    val fecha: String,
    val hora: String
)