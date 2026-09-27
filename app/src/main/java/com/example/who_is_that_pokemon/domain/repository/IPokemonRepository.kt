package com.example.who_is_that_pokemon.domain.repository

import com.example.who_is_that_pokemon.screens.common.model.InitialPokemonState
import com.example.who_is_that_pokemon.data.dto.PokemonDTO
import com.example.who_is_that_pokemon.data.dto.SpecieDetailsDTO
import retrofit2.Response

interface IPokemonRepository {
    suspend fun getInitialPokemon(): Response<InitialPokemonState>

    suspend fun getPokemonByNameOrId(name: String): Response<PokemonDTO>

    suspend fun getPokemonSpecieByName(name: String): Response<SpecieDetailsDTO>

    suspend fun getNext20Pokemon(offset: Int, limit: Int): Response<InitialPokemonState>
}