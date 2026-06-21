package Nos.Fuimos.Mudiales.repository

import Nos.Fuimos.Mudiales.network.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Response

class AuthRepository {

    // Función estricta para descargar la lista de usuarios preexistentes
    suspend fun obtenerUsuarios(): Response<ResponseBody> {
        return RetrofitClient.authApi.obtenerUsuarios()
    }
}