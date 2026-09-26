package com.example.who_is_that_pokemon.model.entity

import com.google.gson.annotations.SerializedName

data class PokemonDescription(
    @SerializedName("flavor_text")
    var text: String
)