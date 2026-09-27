package com.example.who_is_that_pokemon.data.dto

import com.google.gson.annotations.SerializedName

data class StatsDTO(
    @SerializedName("base_stat")
    var value: Float,
    @SerializedName("stat")
    var stat: StatDTO
)

data class StatDTO(
    @SerializedName("name")
    val statName: String,
)