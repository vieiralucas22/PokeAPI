package com.example.who_is_that_pokemon.model.entity

import com.google.gson.annotations.SerializedName

data class SpecieDetails(
    @SerializedName("color")
    val pokemonColor: PokemonColor,
    @SerializedName("flavor_text_entries")
    val descriptions: List<PokemonDescription>
)
