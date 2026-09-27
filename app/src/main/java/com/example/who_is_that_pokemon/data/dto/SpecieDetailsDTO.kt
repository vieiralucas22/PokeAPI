package com.example.who_is_that_pokemon.data.dto

import com.google.gson.annotations.SerializedName

data class SpecieDetailsDTO(
    @SerializedName("color")
    val pokemonColor: PokemonColorDTO,
    @SerializedName("flavor_text_entries")
    val descriptions: List<PokemonDescriptionDTO>
)
