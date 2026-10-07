package com.example.who_is_that_pokemon.domain.repository

import com.example.who_is_that_pokemon.common.concurrency.CoroutineDispatcherProvider
import com.example.who_is_that_pokemon.data.dto.InitialPokemonDTO
import com.example.who_is_that_pokemon.data.dto.PokemonDTO
import com.example.who_is_that_pokemon.data.dto.SpecieDetailsDTO
import com.example.who_is_that_pokemon.data.service.PokemonService
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonService,
    private val dispatcherProvider: CoroutineDispatcherProvider
) : PokemonRepository {

    override suspend fun getNext20Pokemon(
        offset: Int,
        limit: Int
    ): Response<InitialPokemonDTO> = withContext(dispatcherProvider.io) {
        pokemonService.getSomePokemon(offset, limit)
    }

    override suspend fun getPokemonByNameOrId(name: String): Response<PokemonDTO> =
        withContext(dispatcherProvider.io) {
            pokemonService.getPokemonByNameOrId(name)
        }

    override suspend fun getPokemonSpecieByName(name: String): Response<SpecieDetailsDTO> =
        withContext(dispatcherProvider.io) {
            pokemonService.getPokemonSpecieByName(name)
        }

}