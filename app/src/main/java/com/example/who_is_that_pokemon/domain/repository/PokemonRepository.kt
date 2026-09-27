package com.example.who_is_that_pokemon.domain.repository

import com.example.who_is_that_pokemon.data.dto.InitialPokemonDTO
import com.example.who_is_that_pokemon.data.dto.PokemonDTO
import com.example.who_is_that_pokemon.data.dto.SpecieDetailsDTO
import retrofit2.Response

interface PokemonRepository {
    suspend fun getNext20Pokemon(offset: Int, limit: Int): Response<InitialPokemonDTO>

    suspend fun getPokemonByNameOrId(name: String): Response<PokemonDTO>

    suspend fun getPokemonSpecieByName(name: String): Response<SpecieDetailsDTO>

}