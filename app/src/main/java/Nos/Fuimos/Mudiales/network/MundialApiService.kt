package Nos.Fuimos.Mudiales.network

import retrofit2.http.GET
import Nos.Fuimos.Mudiales.models.DTOPartidosLista
import Nos.Fuimos.Mudiales.models.DTOPartidosDetalle

interface MundialApiService {

    // Endpoint real para el Listado General en tu backend de .NET
    @GET("api/partidos")
    suspend fun getPartidosLista(): List<DTOPartidosLista>

    // Endpoint real para el Detalle que consume el mismo fixture completo
    @GET("api/partidos")
    suspend fun getPartidosDetalle(): List<DTOPartidosDetalle>
}