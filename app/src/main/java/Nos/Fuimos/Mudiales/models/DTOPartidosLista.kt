package Nos.Fuimos.Mudiales.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName // <-- AGREGAR ESTO

@Serializable
data class DTOPartidosLista(
        val id: Int,
        @SerialName("local") val equipo1: String,
        @SerialName("visitante") val equipo2: String,
        val grupo: String,
        @SerialName("fechaHora") val fecha: String,
        @SerialName("banderaLocal") val flag1: String,
        @SerialName("banderaVisitante") val flag2: String
)