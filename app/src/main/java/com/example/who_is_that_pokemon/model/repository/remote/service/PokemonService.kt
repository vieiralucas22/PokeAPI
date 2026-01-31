package com.example.who_is_that_pokemon.model.repository.remote.service

import com.example.who_is_that_pokemon.model.entity.InitialPokemonResponse
import com.example.who_is_that_pokemon.model.entity.Pokemon
import retrofit2.Response
import retrofit2.http.GET

interface PokemonService {

    @GET("pokemon")
    suspend fun getSomePokemon() : Response<InitialPokemonResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonById(id : Int) : Response<Pokemon>

}