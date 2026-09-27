package com.example.who_is_that_pokemon.screens.common.model

data class StatsState(
    var value: Float,
    var stat: StatState
)

data class StatState(
    val statName: String,
)