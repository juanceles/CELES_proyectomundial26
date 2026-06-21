package Nos.Fuimos.Mudiales.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import Nos.Fuimos.Mudiales.repository.AuthRepository
import kotlinx.coroutines.launch
import org.json.JSONArray

sealed interface LoginState {
    object Idle : LoginState
    object Loading : LoginState
    object Success : LoginState
    data class Error(val mensaje: String) : LoginState
}

sealed interface RegistroState {
    object Idle : RegistroState
    object Loading : RegistroState
    object Success : RegistroState
    data class Error(val mensaje: String) : RegistroState
}

// Clase auxiliar para modelar el usuario guardado temporalmente en la memoria
data class UsuarioLocal(val usuario: String, val contrasenia: String)

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()

    // VARIABLE LOCAL: Lista en memoria estática que vive mientras la app esté abierta
    companion object {
        val usuariosLocales = mutableListOf<UsuarioLocal>()
    }

    var usuarioInput by mutableStateOf("")
    var contraseniaInput by mutableStateOf("")
    var loginState by mutableStateOf<LoginState>(LoginState.Idle)
        private set

    var registroUsuarioInput by mutableStateOf("")
    var registroCorreoInput by mutableStateOf("")
    var registroContraseniaInput by mutableStateOf("")
    var registroState by mutableStateOf<RegistroState>(RegistroState.Idle)
        private set

    fun iniciarSesion() {
        if (usuarioInput.isBlank() || contraseniaInput.isBlank()) {
            loginState = LoginState.Error("Los campos no pueden estar vacíos")
            return
        }
        // VALIDACIÓN: Contraseña entre 4 y 8 dígitos/caracteres
        if (contraseniaInput.length < 4 || contraseniaInput.length > 8) {
            loginState = LoginState.Error("La contraseña debe tener entre 4 y 8 caracteres")
            return
        }

        viewModelScope.launch {
            loginState = LoginState.Loading
            try {
                // 1. CHEQUEAR EN LA VARIABLE LOCAL PRIMERO
                val encontradoEnLocal = usuariosLocales.any {
                    it.usuario == usuarioInput && it.contrasenia == contraseniaInput
                }

                if (encontradoEnLocal) {
                    loginState = LoginState.Success
                    return@launch
                }

                // 2. SI NO ESTÁ AHÍ, BUSCAR EN MOCKI LOS PREEXISTENTES
                val response = repository.obtenerUsuarios()
                if (response.isSuccessful) {
                    val jsonString = response.body()?.string() ?: "[]"
                    val jsonArray = JSONArray(jsonString)
                    var usuarioEncontrado = false

                    for (i in 0 until jsonArray.length()) {
                        val obj = jsonArray.getJSONObject(i)
                        val userObj = obj.optString("usuario")
                        val passObj = obj.optString("contrasenia")

                        if (userObj == usuarioInput && passObj == contraseniaInput) {
                            usuarioEncontrado = true
                            break
                        }
                    }

                    if (usuarioEncontrado) {
                        loginState = LoginState.Success
                    } else {
                        loginState = LoginState.Error("Usuario o contraseña incorrectos")
                    }
                } else {
                    loginState = LoginState.Error("Error al conectar con el servidor de usuarios")
                }
            } catch (e: Exception) {
                loginState = LoginState.Error("Error de conexión: ${e.localizedMessage}")
            }
        }
    }

    fun registrarUsuario() {
        if (registroUsuarioInput.isBlank() || registroCorreoInput.isBlank() || registroContraseniaInput.isBlank()) {
            registroState = RegistroState.Error("Todos los campos son obligatorios")
            return
        }
        if (!registroCorreoInput.contains("@")) {
            registroState = RegistroState.Error("El correo electrónico no es válido")
            return
        }
        // VALIDACIÓN: Contraseña entre 4 y 8 dígitos/caracteres
        if (registroContraseniaInput.length < 4 || registroContraseniaInput.length > 8) {
            registroState = RegistroState.Error("La contraseña debe tener entre 4 y 8 caracteres")
            return
        }

        viewModelScope.launch {
            registroState = RegistroState.Loading
            try {
                // GUARDAR EN VARIABLE LOCAL
                val nuevoUsuario = UsuarioLocal(
                    usuario = registroUsuarioInput,
                    contrasenia = registroContraseniaInput
                )
                usuariosLocales.add(nuevoUsuario)

                registroState = RegistroState.Success
            } catch (e: Exception) {
                registroState = RegistroState.Error("Error al guardar de forma local: ${e.localizedMessage}")
            }
        }
    }

    fun resetState() {
        loginState = LoginState.Idle
        registroState = RegistroState.Idle
    }
}