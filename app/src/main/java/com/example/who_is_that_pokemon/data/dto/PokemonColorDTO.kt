package com.example.who_is_that_pokemon.data.dto

import com.google.gson.annotations.SerializedName

data class PokemonColorDTO(
    @SerializedName("name")
    val colorName : String,
)
