package com.example.who_is_that_pokemon.model.entity

import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("front_default")
    var default : String = "",
    @SerializedName("front_shiny")
    var shiny : String = ""
)
