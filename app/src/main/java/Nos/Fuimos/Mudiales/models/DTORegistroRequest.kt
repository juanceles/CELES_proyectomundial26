package Nos.Fuimos.Mudiales.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class DTORegistroRequest(
    @SerialName("Nombre") val nombre: String,
    @SerialName("Apellido") val apellido: String,
    @SerialName("Email") val email: String,
    @SerialName("Password") val contrasena: String,
    @SerialName("Telefono") val telefono: String,
    @SerialName("TipoDocumento") val tipoDoc: String,
    @SerialName("NumeroDocumento") val numDoc: String,
    @SerialName("FechaNacimiento") val fechaNac: String,
    @SerialName("Genero") val genero: String,
    @SerialName("PaisResidencia") val pais: String
)