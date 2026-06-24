package com.example.saludify.domain.model

data class Usuario(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val dni: String,
    val email: String,
    val password: String
)