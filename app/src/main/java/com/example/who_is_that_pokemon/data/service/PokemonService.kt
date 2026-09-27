package com.example.who_is_that_pokemon.data.service

import com.example.who_is_that_pokemon.screens.common.model.InitialPokemonState
import com.example.who_is_that_pokemon.data.dto.PokemonDTO
import com.example.who_is_that_pokemon.data.dto.SpecieDetailsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getSomePokemon(): Response<InitialPokemonState>

    @GET("pokemon/{nameOrId}")
    suspend fun getPokemonByNameOrId(@Path("nameOrId") nameOrId: String): Response<PokemonDTO>

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecieByName(@Path("name") name: String): Response<SpecieDetailsDTO>

    @GET("pokemon")
    suspend fun getNext20Pokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<InitialPokemonState>
}