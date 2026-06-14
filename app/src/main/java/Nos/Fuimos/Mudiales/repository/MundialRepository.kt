package Nos.Fuimos.Mudiales.repository

import Nos.Fuimos.Mudiales.models.DTOPartidosDetalle
import Nos.Fuimos.Mudiales.models.DTOPartidosLista
import Nos.Fuimos.Mudiales.network.RetrofitClient

class MundialRepository {

    // 1️⃣ Descarga la lista completa de partidos desde internet
    suspend fun fetchPartidosLista(): List<DTOPartidosLista> {
        return RetrofitClient.api.getPartidosLista()
    }

    // 2️⃣ Descarga los detalles desde internet y filtra por el ID seleccionado
    suspend fun fetchPartidoDetalle(id: Int): DTOPartidosDetalle {
        val listaDetallesInternet = RetrofitClient.api.getPartidosDetalle()

        // Busca el partido en el JSON de internet. Si no existe, devuelve un objeto vacío.
        return listaDetallesInternet.firstOrNull { it.id == id }
            ?: DTOPartidosDetalle(id, "No encontrado", "No encontrado", "-", "-", "", "", "$0", "Sin Sede")
    }

    // Mantenemos esta función vacía por si tu ViewModel la referencia en el catch viejo
    fun fetchPartidosListaLocal(): List<DTOPartidosLista> = emptyList()
}
/*
    // 📌 Plan de contingencia local si se cae internet en la entrega
    private fun fetchPartidoDetalleLocalRespaldo(id: Int): DTOPartidosDetalle {
        val partidosOficiales = listOf(
            DTOPartidosDetalle(1, "Argentina", "Argelia", "Grupo J - Fecha 1", "Martes 16 de Junio - 22:00 hs", "arg_flag", "algeria_flag", "$180", "Kansas City Stadium"),
            DTOPartidosDetalle(2, "Argentina", "Austria", "Grupo J - Fecha 2", "Lunes 22 de Junio - 14:00 hs", "arg_flag", "austria_flag", "$210", "Dallas Stadium"),
            DTOPartidosDetalle(3, "Jordania", "Argentina", "Grupo J - Fecha 3", "Sábado 27 de Junio - 23:00 hs", "jordan_flag", "arg_flag", "$195", "Dallas Stadium")
        )
        return partidosOficiales.firstOrNull { it.id == id }
            ?: DTOPartidosDetalle(id, "Desconocido", "Desconocido", "-", "-", "", "", "$0", "Sin Sede")
    }

    // 📌 Mantenemos esta función por compatibilidad temporal si tu ViewModel la usa
    fun fetchPartidosListaLocal(): List<DTOPartidosLista> {
        return listOf(
            DTOPartidosLista(1, "Argentina", "Argelia", "Grupo J - Fecha 1", "16 de Junio - 22:00 hs", "arg_flag", "algeria_flag"),
            DTOPartidosLista(2, "Argentina", "Austria", "Grupo J - Fecha 2", "22 de Junio - 14:00 hs", "arg_flag", "austria_flag"),
            DTOPartidosLista(3, "Jordania", "Argentina", "Grupo J - Fecha 3", "27 de Junio - 23:00 hs", "jordan_flag", "arg_flag")
        )
    }
}

*/
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