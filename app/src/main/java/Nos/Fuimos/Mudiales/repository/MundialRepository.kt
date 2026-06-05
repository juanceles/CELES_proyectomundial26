package Nos.Fuimos.Mudiales.repository

import Nos.Fuimos.Mudiales.models.DTOPartidosDetalle
import Nos.Fuimos.Mudiales.models.DTOPartidosLista
import Nos.Fuimos.Mudiales.network.MundialApiService

class MundialRepository(private val api: MundialApiService) {

    suspend fun fetchPartidosLista(): List<DTOPartidosLista> {
        return api.getPartidosLista()
    }

    suspend fun fetchPartidosDetalle(): List<DTOPartidosDetalle> {
        return api.getPartidosDetalle()[0]
    }
}