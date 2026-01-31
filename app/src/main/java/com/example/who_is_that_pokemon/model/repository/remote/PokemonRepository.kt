package com.example.who_is_that_pokemon.model.repository.remote

import android.app.Application
import com.example.who_is_that_pokemon.model.entity.InitialPokemonResponse
import com.example.who_is_that_pokemon.model.entity.Pokemon
import com.example.who_is_that_pokemon.model.repository.remote.retrofit.RetrofitClient
import com.example.who_is_that_pokemon.model.repository.remote.service.PokemonService
import retrofit2.Response

class PokemonRepository(application: Application) : BaseRepository(application) {

    private val pokemonService = RetrofitClient.getService(PokemonService::class.java)

    suspend fun getInitialPokemon() : Response<InitialPokemonResponse>
    {
        return safeApiCall {
            pokemonService.getSomePokemon()
        }
    }

    suspend fun getPokemonById(id : Int) : Response<Pokemon>
    {
        return safeApiCall {
            pokemonService.getPokemonById(id)
        }
    }

}