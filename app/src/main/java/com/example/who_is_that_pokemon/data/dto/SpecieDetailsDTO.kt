package com.example.who_is_that_pokemon.data.dto

import com.example.who_is_that_pokemon.screens.common.model.PokemonColorState
import com.google.gson.annotations.SerializedName

data class SpecieDetailsDTO(
    @SerializedName("color")
    val pokemonColor: PokemonColorState,
    @SerializedName("flavor_text_entries")
    val descriptions: List<PokemonDescriptionDTO>
)
