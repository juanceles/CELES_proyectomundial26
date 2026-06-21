package Nos.Fuimos.Mudiales.network

import retrofit2.http.GET
import Nos.Fuimos.Mudiales.models.DTOPartidosLista
import Nos.Fuimos.Mudiales.models.DTOPartidosDetalle

interface MundialApiService {

    // Endpoint para el Listado General de Partidos
    @GET(value = "v1/76f0acff-5d49-4c73-99d8-842da8fef40c")
    suspend fun getPartidosLista(): List<DTOPartidosLista>

    // Endpoint para el Detalle Extendido de Partidos
    @GET(value = "v1/a0626b69-fdab-4c33-b2eb-a4b94e6a2d02")
    suspend fun getPartidosDetalle(): List<DTOPartidosDetalle>
}