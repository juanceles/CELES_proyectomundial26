package Nos.Fuimos.Mudiales.network

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import Nos.Fuimos.Mudiales.network.MundialApiService
import Nos.Fuimos.Mudiales.network.AuthApiService

object RetrofitClient {
    private val json = Json { ignoreUnknownKeys = true }

    // IP real de tu placa Wi-Fi y puerto de .NET unificados sin cortes
    private val BASE_URL = "http://127.0.0"

    val api: MundialApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(MundialApiService::class.java)
    }

    val authApi: AuthApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(AuthApiService::class.java)
    }
}