package com.example.who_is_that_pokemon.data.dto

import com.google.gson.annotations.SerializedName

data class StatsDTO(
    @SerializedName("base_stat")
    val value: Float,
    @SerializedName("stat")
    val stat: StatDTO
)

data class StatDTO(
    @SerializedName("name")
    val statName: String,
)