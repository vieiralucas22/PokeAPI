package com.example.who_is_that_pokemon.data.dto

import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName

data class PokemonDTO(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("height")
    var height: Double,
    @SerializedName("weight")
    var weight: Double,
    @SerializedName("types")
    var types: List<TypeSlotDTO>,
    @SerializedName("sprites")
    var spritesDTO: SpritesDTO,
    @SerializedName("stats")
    var stats: List<StatsDTO>,
    var color: Color
)
