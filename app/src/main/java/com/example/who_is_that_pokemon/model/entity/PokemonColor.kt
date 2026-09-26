package com.example.who_is_that_pokemon.model.entity

import com.google.gson.annotations.SerializedName

data class PokemonColor(
    @SerializedName("name")
    val colorName : String,
)
