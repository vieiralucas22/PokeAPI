package com.example.who_is_that_pokemon.domain.usecase

import com.example.who_is_that_pokemon.data.dto.PokemonDTO
import com.example.who_is_that_pokemon.domain.repository.PokemonRepository
import com.example.who_is_that_pokemon.screens.common.model.InitialPokemonState
import com.example.who_is_that_pokemon.screens.home.converter.NextPokemonConverter
import javax.inject.Inject

class LoadPokemonUseCaseImpl @Inject constructor(
    private val _pokemonRepository: PokemonRepository,
    private val _converterNextPokemon: NextPokemonConverter
) : LoadPokemonUseCase {

    override suspend fun invoke(offset: Int, limit: Int): InitialPokemonState? {

        val response = _pokemonRepository.getNext20Pokemon(offset, limit)

        if (response.isSuccessful && response.body() != null) {
            val body = response.body()
            body?.let { pokemon ->
                val detailedPokemon = pokemon.allPokemon.map { getPokemonDetails(it) }
                return _converterNextPokemon.convert(pokemon.copy(allPokemon = detailedPokemon))
            }
        }

        return null
    }

    private suspend fun getPokemonDetails(pokemon: PokemonDTO): PokemonDTO {
        val response = _pokemonRepository.getPokemonByNameOrId(pokemon.name)

        if (response.isSuccessful && response.body() != null) {
            val body = response.body()

            body?.let {
                return it
            }
        }
        return pokemon
    }

}
