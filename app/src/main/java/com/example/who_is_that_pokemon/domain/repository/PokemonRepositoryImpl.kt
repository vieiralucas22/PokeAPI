package com.example.who_is_that_pokemon.domain.repository

import com.example.who_is_that_pokemon.data.dto.InitialPokemonDTO
import com.example.who_is_that_pokemon.screens.common.model.InitialPokemonState
import com.example.who_is_that_pokemon.data.dto.PokemonDTO
import com.example.who_is_that_pokemon.data.dto.SpecieDetailsDTO
import com.example.who_is_that_pokemon.data.service.PokemonService
import retrofit2.Response
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val _pokemonService: PokemonService
) : IPokemonRepository {

    override suspend fun getInitialPokemon(): Response<InitialPokemonDTO> =
        _pokemonService.getSomePokemon()

    override suspend fun getPokemonByNameOrId(name: String): Response<PokemonDTO> =
        _pokemonService.getPokemonByNameOrId(name)


    override suspend fun getPokemonSpecieByName(name: String): Response<SpecieDetailsDTO> =
        _pokemonService.getPokemonSpecieByName(name)

    override suspend fun getNext20Pokemon(
        offset: Int,
        limit: Int
    ): Response<InitialPokemonState> =
        _pokemonService.getNext20Pokemon(offset, limit)
}