package com.example.who_is_that_pokemon.screens.common.model

import com.example.who_is_that_pokemon.R

data class PokemonState(
    val id: Int,
    val name: String,
    val height: Double,
    val weight: Double,
    val types: List<TypeSlotState>,
    val sprites: SpritesState,
    val stats: List<StatsState>,
    val specieDetails: SpecieDetailsState
) {
    fun getPokemonColorId(): Int =
        when (this.specieDetails.pokemonColor.colorName.lowercase()) {
            "red" -> R.color.pokemon_red
            "blue" -> R.color.pokemon_blue
            "yellow" -> R.color.pokemon_yellow
            "green" -> R.color.pokemon_green
            "black" -> R.color.pokemon_black
            "white" -> R.color.pokemon_white
            "gray" -> R.color.pokemon_gray
            "pink" -> R.color.pokemon_pink
            "purple" -> R.color.pokemon_purple
            "brown" -> R.color.pokemon_brown
            else -> R.color.pokemon_default
        }
}
