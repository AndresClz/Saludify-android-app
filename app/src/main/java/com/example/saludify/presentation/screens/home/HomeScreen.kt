package com.example.saludify.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.saludify.data.MockData

@Composable
fun HomeScreen() {
    val usuario = MockData.currentUser ?: MockData.usuarios.first()
    val turno = MockData.proximoTurno

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Bienvenido ${usuario.nombre}",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Credencial Digital")
                Text("Afiliado: ${usuario.nombre} ${usuario.apellido}")
                Text("DNI: ${usuario.dni}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Próximo Turno")
                Text(turno.especialidad)
                Text("${turno.fecha} - ${turno.hora}")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Accesos rápidos")
        Spacer(modifier = Modifier.height(8.dp))

        Button(modifier = Modifier.fillMaxWidth(), onClick = {}) { Text("Sacar Turno") }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {}) { Text("Cartilla Médica") }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {}) { Text("Reintegros") }
    }
}
