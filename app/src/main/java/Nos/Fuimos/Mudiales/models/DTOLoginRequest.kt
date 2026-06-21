package Nos.Fuimos.Mudiales.models

import kotlinx.serialization.Serializable

@Serializable
data class DTOLoginRequest(
    val usuario: String,
    val contrasenia: String
)