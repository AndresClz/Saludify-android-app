package com.example.saludify.presentation.screens.login

import androidx.lifecycle.ViewModel
import com.example.saludify.data.MockData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    private val _dni = MutableStateFlow("")
    val dni: StateFlow<String> = _dni

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun onDniChange(value: String) { _dni.value = value }
    fun onPasswordChange(value: String) { _password.value = value }

    fun login(onSuccess: () -> Unit) {
        if (dni.value.isBlank()) { _errorMessage.value = "Ingrese su DNI"; return }
        if (password.value.isBlank()) { _errorMessage.value = "Ingrese su contraseña"; return }
        val user = MockData.autenticar(dni.value, password.value)
        if (user != null) {
            MockData.currentUser = user
            _errorMessage.value = null
            onSuccess()
        } else {
            _errorMessage.value = "DNI o contraseña incorrectos"
        }
    }
}
