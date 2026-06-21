package Nos.Fuimos.Mudiales.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import Nos.Fuimos.Mudiales.viewmodels.AuthViewModel
import Nos.Fuimos.Mudiales.viewmodels.RegistroState

@Composable
fun RegistroScreen(
    viewModel: AuthViewModel,
    onRegistroSuccess: () -> Unit,
    onVolverClick: () -> Unit
) {
    val state = viewModel.registroState

    if (state is RegistroState.Success) {
        onRegistroSuccess()
        viewModel.resetState()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Crear Cuenta",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Yellow,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Campo: Nombre de Usuario
            OutlinedTextField(
                value = viewModel.registroUsuarioInput,
                onValueChange = {
                    viewModel.registroUsuarioInput = it
                    viewModel.resetState()
                },
                label = { Text("Nombre de Usuario") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF007F00),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color.Yellow,
                    unfocusedLabelColor = Color.LightGray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo: Correo Electrónico
            OutlinedTextField(
                value = viewModel.registroCorreoInput,
                onValueChange = {
                    viewModel.registroCorreoInput = it
                    viewModel.resetState()
                },
                label = { Text("Correo Electrónico") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF007F00),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color.Yellow,
                    unfocusedLabelColor = Color.LightGray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo: Contraseña
            OutlinedTextField(
                value = viewModel.registroContraseniaInput,
                onValueChange = {
                    viewModel.registroContraseniaInput = it
                    viewModel.resetState()
                },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF007F00),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color.Yellow,
                    unfocusedLabelColor = Color.LightGray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Estado de carga o Mensaje de Error
            when (state) {
                is RegistroState.Loading -> {
                    CircularProgressIndicator(color = Color.Yellow)
                    Spacer(modifier = Modifier.height(16.dp))
                }
                is RegistroState.Error -> {
                    Text(
                        text = state.mensaje,
                        color = Color.Red,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                else -> {}
            }

            // Botón Registrarse
            Button(
                onClick = { viewModel.registrarUsuario() },
                enabled = state !is RegistroState.Loading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF007F00),
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Registrarse",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Enlace para volver atrás al Login
            TextButton(onClick = onVolverClick) {
                Text(
                    text = "¿Ya tienes cuenta? Inicia Sesión",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }
        }
    }
}