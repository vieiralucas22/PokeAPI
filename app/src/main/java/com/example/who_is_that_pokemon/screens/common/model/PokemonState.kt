package com.example.who_is_that_pokemon.screens.common.model

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
    fun getPokemonColor(): Int? = types.firstOrNull()?.typeState?.getTypeColor()
}
