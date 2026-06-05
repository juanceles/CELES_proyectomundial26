package Nos.Fuimos.Mudiales.network

import Nos.Fuimos.Mudiales.models.DTOPartidosDetalle
import Nos.Fuimos.Mudiales.models.DTOPartidosLista
import retrofit2.http.GET

interface MundialApiService {

    @GET("PartidoLista")
    suspend fun getPartidosLista(): List<DTOPartidosLista>

    @GET("PartidoDetalle")
    suspend fun getPartidosDetalle(): List<DTOPartidosDetalle>
}