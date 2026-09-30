package com.example.who_is_that_pokemon.screens.pokemondetails.model

import com.example.who_is_that_pokemon.screens.common.model.PokemonState

sealed interface PokemonDetailsUIState {
    data class Success(val data: PokemonState) : PokemonDetailsUIState

    data object Loading : PokemonDetailsUIState

    data class Error(val message: String) : PokemonDetailsUIState
}