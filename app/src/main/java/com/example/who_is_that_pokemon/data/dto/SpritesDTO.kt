package com.example.who_is_that_pokemon.data.dto

import com.google.gson.annotations.SerializedName

data class SpritesDTO(
    @SerializedName("front_default")
    var default: String = "",
    @SerializedName("front_shiny")
    var shiny: String = ""
)
