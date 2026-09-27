package com.example.who_is_that_pokemon.screens.common.model

import androidx.compose.ui.graphics.Color
import com.example.who_is_that_pokemon.data.dto.StatsDTO
import com.example.who_is_that_pokemon.data.dto.TypeSlotDTO

data class PokemonState(
    val id: Int,
    val name: String,
    val height: Double,
    val weight: Double,
    val types: List<TypeSlotDTO>,
    val spritesDTO: SpritesState,
    val stats: List<StatsDTO>,
    val color: Color
)
