package com.example.saludify.data

import com.example.saludify.domain.model.ContactoTelefono
import com.example.saludify.domain.model.Especialidad
import com.example.saludify.domain.model.FaqItem
import com.example.saludify.domain.model.Medico
import com.example.saludify.domain.model.MedicoCartilla
import com.example.saludify.domain.model.ObraSocial
import com.example.saludify.domain.model.Sucursal
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

    val faqItems = listOf(
        FaqItem(
            pregunta = "¿Cómo saco un turno médico?",
            respuesta = "Andá a Atención → Sacar turno, elegí especialidad, médico y horario. Recibirás confirmación por notificación push."
        ),
        FaqItem(
            pregunta = "¿Cómo pido una autorización?",
            respuesta = "Desde Trámites → Autorizaciones podés solicitar autorizaciones médicas. El tiempo de respuesta es de 24 a 72 hs hábiles."
        ),
        FaqItem(
            pregunta = "¿Qué cubre mi plan Premium?",
            respuesta = "El plan Premium incluye consultas médicas, estudios de laboratorio, imágenes, internaciones y medicamentos con cobertura del 80%."
        ),
        FaqItem(
            pregunta = "¿Cómo cargo un reintegro?",
            respuesta = "Ingresá a Trámites → Reintegros, adjuntá la factura y comprobante de pago. El reintegro se acredita en 15 días hábiles."
        )
    )

    val telefonos = listOf(
        ContactoTelefono(
            label = "Urgencias 24 hs",
            numero = "0800 333 4444",
            badgeText = "Llamada sin cargo",
            esUrgencias = true
        ),
        ContactoTelefono(
            label = "Atención al afiliado",
            numero = "0800 222 7777",
            badgeText = "Lun–Vie 8–20",
            esUrgencias = false
        )
    )

    val sucursales = listOf(
        Sucursal(
            nombre = "Sucursal Centro",
            direccion = "Av. Corrientes 1234, piso 2",
            ciudad = "Buenos Aires, CABA",
            horario = "Lun–Vie 8:00–18:00",
            telefono = "01145678900",
            distanciaTexto = "0.8 km",
            tiempoEstimado = "~10 min caminando",
            estaAbierto = true,
            statusLabel = "Abierto ahora",
            horarioApertura = null,
            esMasCercana = true
        ),
        Sucursal(
            nombre = "Sucursal Palermo",
            direccion = "Thames 1880, planta baja",
            ciudad = "Buenos Aires, CABA",
            horario = "Lun–Sáb 9:00–17:00",
            telefono = "01145679100",
            distanciaTexto = "2.3 km",
            tiempoEstimado = "~28 min caminando",
            estaAbierto = true,
            statusLabel = "Abierto ahora",
            horarioApertura = null,
            esMasCercana = false
        ),
        Sucursal(
            nombre = "Sucursal Belgrano",
            direccion = "Av. Cabildo 2150, local 4",
            ciudad = "Buenos Aires, CABA",
            horario = "Lun–Vie 9:00–18:00",
            telefono = "01145679200",
            distanciaTexto = "4.1 km",
            tiempoEstimado = "~12 min en auto",
            estaAbierto = false,
            statusLabel = "Cerrado ahora",
            horarioApertura = "Abre lunes 8:00",
            esMasCercana = false
        )
    )

    var currentUser: Usuario? = null

    fun autenticar(dni: String, password: String): Usuario? =
        usuarios.find { it.dni == dni && it.password == password }
}
