package com.example.who_is_that_pokemon.data.entity

import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("base_stat")
    var value: Float,
    @SerializedName("stat")
    var stat: Stat
)

data class Stat(
    @SerializedName("name")
    val statName: String,
)