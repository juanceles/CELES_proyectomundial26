package Nos.Fuimos.Mudiales.repository

import Nos.Fuimos.Mudiales.models.DTOPartidosDetalle
import Nos.Fuimos.Mudiales.models.DTOPartidosLista

class MundialRepository {

    fun fetchPartidosListaLocal(): List<DTOPartidosLista> {
        return listOf(
            DTOPartidosLista(1, "Argentina", "Argelia", "Grupo J - Fecha 1", "16 de Junio - 22:00 hs", "arg_flag", "algeria_flag"),
            DTOPartidosLista(2, "Argentina", "Austria", "Grupo J - Fecha 2", "22 de Junio - 14:00 hs", "arg_flag", "austria_flag"),
            DTOPartidosLista(3, "Jordania", "Argentina", "Grupo J - Fecha 3", "27 de Junio - 23:00 hs", "jordan_flag", "arg_flag")
        )
    }

    fun fetchPartidoDetalleLocal(id: Int): DTOPartidosDetalle {
        val partidosOficiales = listOf(
            DTOPartidosDetalle(1, "Argentina", "Argelia", "Grupo J - Fecha 1", "Martes 16 de Junio", "arg_flag", "algeria_flag", "$180", "Kansas City Stadium"),
            DTOPartidosDetalle(2, "Argentina", "Austria", "Grupo J - Fecha 2", "Lunes 22 de Junio", "arg_flag", "austria_flag", "$210", "Dallas Stadium"),
            DTOPartidosDetalle(3, "Jordania", "Argentina", "Grupo J - Fecha 3", "Sábado 27 de Junio", "jordan_flag", "arg_flag", "$195", "Dallas Stadium")
        )
        return partidosOficiales.firstOrNull { it.id == id }
            ?: DTOPartidosDetalle(id, "Desconocido", "Desconocido", "-", "-", "", "", "$0", "Sin Sede")
    }
}

/*
    =======================================================================
    🌎 CÓDIGO DE RED COMENTADO - REUTILIZABLE PARA PRÓXIMOS TP
    =======================================================================
    suspend fun fetchPartidosLista(): List<DTOPartidosLista> {
        return api.getPartidosLista()
    }

    suspend fun fetchPartidosDetalle(): List<DTOPartidosDetalle> {
        return api.getPartidosDetalle()
    }
    =======================================================================
    */