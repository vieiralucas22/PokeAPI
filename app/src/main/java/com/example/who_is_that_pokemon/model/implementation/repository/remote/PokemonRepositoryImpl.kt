package com.example.who_is_that_pokemon.model.implementation.repository.remote

import com.example.who_is_that_pokemon.model.entity.InitialPokemonResponse
import com.example.who_is_that_pokemon.model.entity.Pokemon
import com.example.who_is_that_pokemon.model.entity.SpecieDetails
import com.example.who_is_that_pokemon.model.interfaces.repository.remote.IPokemonRepository
import com.example.who_is_that_pokemon.model.repository.remote.service.PokemonService
import retrofit2.Response
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val _pokemonService: PokemonService
) : IPokemonRepository {

    override suspend fun getInitialPokemon(): Response<InitialPokemonResponse> =
        _pokemonService.getSomePokemon()

    override suspend fun getPokemonByNameOrId(name: String): Response<Pokemon> =
        _pokemonService.getPokemonByNameOrId(name)


    override suspend fun getPokemonSpecieByName(name: String): Response<SpecieDetails> =
        _pokemonService.getPokemonSpecieByName(name)

    override suspend fun getNext20Pokemon(
        offset: Int,
        limit: Int
    ): Response<InitialPokemonResponse> =
        _pokemonService.getNext20Pokemon(offset, limit)
}
