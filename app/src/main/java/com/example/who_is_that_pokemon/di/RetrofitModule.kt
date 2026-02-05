package com.example.who_is_that_pokemon.di

import com.example.who_is_that_pokemon.constants.RetrofitConstants
import com.example.who_is_that_pokemon.model.repository.remote.service.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun providesRetrofitClient(): Retrofit {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Accept", "application/json")
                .build()

            chain.proceed(request)
        }

            return Retrofit.Builder()
                .baseUrl(RetrofitConstants.BASE_POKE_API_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    fun providesPokemonService(retrofitClient: Retrofit): PokemonService
    {
        return retrofitClient.create(PokemonService::class.java)
    }
}