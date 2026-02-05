package com.example.who_is_that_pokemon.model.interfaces.repository.remote

import com.example.who_is_that_pokemon.model.entity.InitialPokemonResponse
import com.example.who_is_that_pokemon.model.entity.Pokemon
import com.example.who_is_that_pokemon.model.entity.SpecieDetails
import retrofit2.Response

interface IPokemonRepository {
    suspend fun getInitialPokemon() : Response<InitialPokemonResponse>

    suspend fun getPokemonByNameOrId(name : String) : Response<Pokemon>

    suspend fun getPokemonSpecieByName(name : String) : SpecieDetails?

    suspend fun getNext20Pokemon(offset : Int, limit : Int) : Response<InitialPokemonResponse>
}