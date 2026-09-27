package com.example.who_is_that_pokemon.screens.common.model

data class InitialPokemonState(
    val total: Int,
    val allPokemon: List<PokemonState>,
    val next20Pokemon: String?
)
