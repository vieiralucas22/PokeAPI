package com.example.who_is_that_pokemon.domain.usecase

import com.example.who_is_that_pokemon.data.dto.PokemonDTO
import com.example.who_is_that_pokemon.domain.repository.PokemonRepository
import com.example.who_is_that_pokemon.screens.common.model.PokemonState
import com.example.who_is_that_pokemon.screens.pokemondetails.converter.PokemonConverter
import javax.inject.Inject

class FindPokemonUseCaseImpl @Inject constructor(
    private val _pokemonRepository: PokemonRepository,
    private val _pokemonConverter: PokemonConverter
) : FindPokemonUseCase {
    override suspend fun invoke(pokemonKey: String): PokemonState? {

        val response = _pokemonRepository.getPokemonByNameOrId(pokemonKey)

        if (response.isSuccessful && response.body() != null) {
            val body = response.body()

            body?.let {
                val detailedPokemon = getPokemonWithSpeciesDetails(body)
                return _pokemonConverter.convert(detailedPokemon)
            }
        }
        return null
    }

    private suspend fun getPokemonWithSpeciesDetails(pokemonDTO: PokemonDTO) : PokemonDTO {
        val response = _pokemonRepository.getPokemonSpecieByName(pokemonDTO.name)

        if (response.isSuccessful && response.body() != null) {
            val body = response.body()

            body?.let {
                return pokemonDTO.copy(specieDetails = it)
            }
        }

        return pokemonDTO
    }
}