package com.example.who_is_that_pokemon.screens.home.model

import com.example.who_is_that_pokemon.screens.common.model.InitialPokemonState

sealed interface HomeUIState {
    data class Success(val data: InitialPokemonState) : HomeUIState
    data object Loading : HomeUIState
    data class Error(val message: String) : HomeUIState
}
