package Nos.Fuimos.Mudiales.network

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitClient {

    private val json = Json { ignoreUnknownKeys = true }

    val api: MundialApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://mocki.io")
            .addConverterFactory(
                json.asConverterFactory(
                    contentType = "application/json".toMediaType()
                )
            )
            .build()
            .create(MundialApiService::class.java)
    }

    val authApi: AuthApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://mocki.io")
            .addConverterFactory(
                json.asConverterFactory(
                    contentType = "application/json".toMediaType()
                )
            )
            .build()
            .create(AuthApiService::class.java)
    }
}