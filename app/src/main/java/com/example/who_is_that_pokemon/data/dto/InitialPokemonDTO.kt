package com.example.who_is_that_pokemon.data.dto

import com.google.gson.annotations.SerializedName

data class InitialPokemonDTO(
    @SerializedName("count")
    val total: Int,
    @SerializedName("results")
    val pokemonDTOS: List<PokemonDTO>,
    @SerializedName("next")
    val next20Pokemons: String
)
