package Nos.Fuimos.Mudiales.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class DTOLoginRequest(
    @SerialName("email") val correo: String,
    @SerialName("password") val contrasena: String
)