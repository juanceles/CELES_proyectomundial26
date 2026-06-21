package Nos.Fuimos.Mudiales.models

import kotlinx.serialization.Serializable

@Serializable
data class DTORegistroRequest(
    val usuario: String,
    val correo: String,
    val contrasenia: String
)