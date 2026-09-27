package com.example.who_is_that_pokemon.data.dto

import com.google.gson.annotations.SerializedName

data class InitialPokemonDTO(
    @SerializedName("count")
    val total : Int,
    @SerializedName("results")
    val allPokemon : List<PokemonDTO>,
    @SerializedName("next")
    val next20Pokemon: String?
)