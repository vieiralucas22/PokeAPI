package com.example.who_is_that_pokemon.data.dto

import com.google.gson.annotations.SerializedName

data class PokemonDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("height")
    val height: Double?,
    @SerializedName("weight")
    val weight: Double?,
    @SerializedName("types")
    val types: List<TypeSlotDTO>?,
    @SerializedName("sprites")
    val sprite: SpritesDTO?,
    @SerializedName("stats")
    val stats: List<StatsDTO>?,
)
