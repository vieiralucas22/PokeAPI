package com.example.who_is_that_pokemon.model.repository.remote.retrofit

import com.example.who_is_that_pokemon.constants.RetrofitConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {
        private lateinit var INSTANCE: Retrofit

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor { chain ->
                val  request = chain.request()
                    .newBuilder()
                    .addHeader("Accept", "application/json")
                    .build()

                chain.proceed(request)
            }

            if (!::INSTANCE.isInitialized) {
                synchronized(RetrofitClient::class) {
                    INSTANCE = Retrofit.Builder()
                        .baseUrl(RetrofitConstants.BASE_POKE_API_URL)
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return INSTANCE
        }

        fun <T> getService(serviceClass: Class<T>): T {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}