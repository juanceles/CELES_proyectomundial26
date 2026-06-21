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
import Nos.Fuimos.Mudiales.viewmodels.LoginState

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onLoginSuccess: () -> Unit,
    onRegistrarseClick: () -> Unit // Nuevo parámetro para ir a registrarse
) {
    val state = viewModel.loginState

    if (state is LoginState.Success) {
        onLoginSuccess()
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
                text = "Iniciar Sesión",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Yellow,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = viewModel.usuarioInput,
                onValueChange = {
                    viewModel.usuarioInput = it
                    viewModel.resetState()
                },
                label = { Text("Correo de Usuario")},
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

            OutlinedTextField(
                value = viewModel.contraseniaInput,
                onValueChange = {
                    viewModel.contraseniaInput = it
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

            when (state) {
                is LoginState.Loading -> {
                    CircularProgressIndicator(color = Color.Yellow)
                    Spacer(modifier = Modifier.height(16.dp))
                }
                is LoginState.Error -> {
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

            Button(
                onClick = { viewModel.iniciarSesion() },
                enabled = state !is LoginState.Loading,
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
                    text = "Ingresar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Enlace de texto agregado abajo para redirigir al alta de cuenta
            TextButton(onClick = onRegistrarseClick) {
                Text(
                    text = "¿No tienes cuenta? Regístrate aquí",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }
        }
    }
}