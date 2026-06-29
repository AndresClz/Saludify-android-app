package com.example.saludify.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.saludify.ui.theme.BackgroundSubtle
import com.example.saludify.ui.theme.BackgroundSurface
import com.example.saludify.ui.theme.BorderDefault
import com.example.saludify.ui.theme.BrandPrimary
import com.example.saludify.ui.theme.DmSans
import com.example.saludify.ui.theme.SaludifyRadius
import com.example.saludify.ui.theme.SemanticDanger
import com.example.saludify.ui.theme.TextDefault
import com.example.saludify.ui.theme.TextMuted
import com.example.saludify.ui.theme.TextOnPrimary
import com.example.saludify.ui.theme.TextPlaceholder

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit,
    onBackClick: () -> Unit = {}
) {
    val dni by viewModel.dni.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSurface)
            .statusBarsPadding()
            .padding(top = 24.dp, start = 22.dp, end = 22.dp, bottom = 38.dp)
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .offset(x = (-12).dp)
                .padding(bottom = 30.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = TextDefault,
                modifier = Modifier.size(22.dp)
            )
        }

        Text(
            text = "Ingresá a\ntu cuenta",
            color = TextDefault,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = (28 * 1.2f).sp,
            letterSpacing = (-0.6).sp
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = "Usá tu DNI como nombre de usuario.",
            color = TextMuted,
            fontSize = 14.sp,
            lineHeight = (14 * 1.6f).sp
        )
        Spacer(Modifier.height(38.dp))

        Column(verticalArrangement = Arrangement.spacedBy(22.dp)) {
            LabeledField(label = "DNI") {
                AuthTextField(
                    value = dni,
                    onValueChange = { viewModel.onDniChange(it.filter(Char::isDigit).take(11)) },
                    placeholder = "12 345 678",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Column {
                LabeledField(label = "CONTRASEÑA") {
                    AuthTextField(
                        value = password,
                        onValueChange = viewModel::onPasswordChange,
                        placeholder = "••••••••",
                        visualTransformation = if (passwordVisible) VisualTransformation.None
                                               else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(
                                onClick = { passwordVisible = !passwordVisible },
                                modifier = Modifier.size(36.dp)
                            ) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Filled.Visibility
                                                  else Icons.Filled.VisibilityOff,
                                    contentDescription = null,
                                    tint = TextPlaceholder,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    )
                }
                Spacer(Modifier.height(9.dp))
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    color = BrandPrimary,
                    fontSize = 12.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        errorMessage?.let {
            Spacer(Modifier.height(16.dp))
            Text(
                text = it,
                color = SemanticDanger,
                fontSize = 13.sp
            )
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = { viewModel.login(onSuccess = onLoginSuccess) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = SaludifyRadius.button,
            colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary)
        ) {
            Text(
                text = "Ingresar",
                style = TextStyle(
                    fontFamily = DmSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = TextOnPrimary
                )
            )
        }
    }
}

@Composable
private fun LabeledField(label: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
        Text(
            text = label,
            color = TextMuted,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.07.em
        )
        content()
    }
}

@Composable
private fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val borderColor = if (isFocused) BrandPrimary else BorderDefault

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundSubtle, RoundedCornerShape(13.dp))
            .border(1.5.dp, borderColor, RoundedCornerShape(13.dp)),
        textStyle = TextStyle(
            fontFamily = DmSans,
            fontSize = 15.sp,
            color = TextDefault
        ),
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(BrandPrimary),
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = TextPlaceholder,
                            fontSize = 15.sp,
                            fontFamily = DmSans
                        )
                    }
                    innerTextField()
                }
                trailingIcon?.invoke()
            }
        }
    )
}
