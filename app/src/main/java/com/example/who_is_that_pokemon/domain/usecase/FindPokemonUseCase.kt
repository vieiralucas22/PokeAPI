package com.example.who_is_that_pokemon.domain.usecase

import com.example.who_is_that_pokemon.screens.common.model.PokemonState

interface FindPokemonUseCase {
    suspend fun invoke(pokemonKey: String) : PokemonState?
}