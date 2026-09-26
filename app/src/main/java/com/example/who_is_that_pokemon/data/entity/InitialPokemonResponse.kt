package com.example.who_is_that_pokemon.data.entity

import com.google.gson.annotations.SerializedName

data class InitialPokemonResponse(
    @SerializedName("count")
    val total: Int,
    @SerializedName("results")
    val pokemons: List<Pokemon>,
    @SerializedName("next")
    val next20Pokemons: String
)
