package com.example.who_is_that_pokemon.screens.common.model

data class StatsState(
    val value: Float,
    val stat: StatState
)

data class StatState(
    val statName: String,
)