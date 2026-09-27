package com.example.who_is_that_pokemon.screens.common.model

import com.example.who_is_that_pokemon.data.dto.PokemonDTO

data class InitialPokemonState(
    val total: Int,
    val pokemonDTOS: List<PokemonDTO>,
    val next20Pokemons: String
)
