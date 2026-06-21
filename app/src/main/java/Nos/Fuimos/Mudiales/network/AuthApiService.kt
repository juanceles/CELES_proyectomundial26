package Nos.Fuimos.Mudiales.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface AuthApiService {
    // Obtenemos de forma estricta la lista de usuarios guardados en Mocki
    @GET("v1/bc04726c-a2ab-417a-813a-d368b3077cbf")
    suspend fun obtenerUsuarios(): Response<ResponseBody>
}