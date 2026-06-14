package Nos.Fuimos.Mudiales.network

import Nos.Fuimos.Mudiales.models.DTOPartidosDetalle
import Nos.Fuimos.Mudiales.models.DTOPartidosLista
import retrofit2.http.GET

interface MundialApiService {

    // 📌 Cambiamos la ruta vieja por el código único de tu servidor de internet
    @GET(value = "v1/e2ca56ec-150c-4efe-89a9-877f57b09878")
    suspend fun getPartidosLista(): List<DTOPartidosLista>

    @GET("v1/860efe96-4f7f-4b04-a414-b589a2e09411")
    suspend fun getPartidosDetalle(): List<DTOPartidosDetalle>
}