package com.example.who_is_that_pokemon.screens.common.model

import com.example.who_is_that_pokemon.R

data class TypeState(
    val name: String,
) {
    fun getTypeColor(): Int {
        return when (this.name.lowercase()) {
            "fire" -> R.color.type_fire
            "water" -> R.color.type_water
            "grass" -> R.color.type_grass
            "electric" -> R.color.type_electric
            "ice" -> R.color.type_ice
            "fighting" -> R.color.type_fighting
            "poison" -> R.color.type_poison
            "ground" -> R.color.type_ground
            "flying" -> R.color.type_flying
            "psychic" -> R.color.type_psychic
            "bug" -> R.color.type_bug
            "rock" -> R.color.type_rock
            "ghost" -> R.color.type_ghost
            "dragon" -> R.color.type_dragon
            "dark" -> R.color.type_dark
            "steel" -> R.color.type_steel
            "fairy" -> R.color.type_fairy
            "normal" -> R.color.type_normal
            else -> R.color.type_default
        }
    }
}
