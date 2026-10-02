package com.example.who_is_that_pokemon.screens.pokemondetails.converter

import com.example.who_is_that_pokemon.data.dto.PokemonDTO
import com.example.who_is_that_pokemon.data.dto.PokemonDescriptionDTO
import com.example.who_is_that_pokemon.data.dto.StatsDTO
import com.example.who_is_that_pokemon.data.dto.TypeSlotDTO
import com.example.who_is_that_pokemon.screens.common.model.PokemonDescriptionState
import com.example.who_is_that_pokemon.screens.common.model.PokemonState
import com.example.who_is_that_pokemon.screens.common.model.SpecieDetailsState
import com.example.who_is_that_pokemon.screens.common.model.SpritesState
import com.example.who_is_that_pokemon.screens.common.model.StatState
import com.example.who_is_that_pokemon.screens.common.model.StatsState
import com.example.who_is_that_pokemon.screens.common.model.TypeSlotState
import com.example.who_is_that_pokemon.screens.common.model.TypeState
import javax.inject.Inject

class PokemonConverter @Inject constructor() {

    fun convert(pokemonDTO: PokemonDTO): PokemonState = PokemonState(
        id = pokemonDTO.id,
        name = pokemonDTO.name,
        height = pokemonDTO.height ?: 0.0,
        weight = pokemonDTO.weight ?: 0.0,
        types = buildPokemonTypes(pokemonDTO.types),
        sprites = SpritesState(
            default = pokemonDTO.sprite?.default ?: "",
            shiny = pokemonDTO.sprite?.shiny ?: ""
        ),
        stats = buildPokemonStats(pokemonDTO.stats),
        specieDetails = SpecieDetailsState(
            descriptions = buildPokemonDescription(pokemonDTO.specieDetails?.descriptions)
        )
    )

    private fun buildPokemonTypes(typesDTO: List<TypeSlotDTO>?): List<TypeSlotState> {

        typesDTO?.let {
            return typesDTO.map { (slot, type) ->
                TypeSlotState(
                    slot = slot,
                    typeState = TypeState(type.name)
                )
            }
        }

        return emptyList()
    }

    private fun buildPokemonStats(statsDTO: List<StatsDTO>?): List<StatsState> {
        statsDTO?.let {
            return statsDTO.map { stat ->
                StatsState(
                    value = stat.value,
                    stat = StatState(stat.stat.statName)
                )
            }
        }

        return emptyList()
    }

    private fun buildPokemonDescription(descriptions: List<PokemonDescriptionDTO>?): List<PokemonDescriptionState> {
        descriptions?.let {
            return descriptions.map { description ->
                PokemonDescriptionState(
                    description.text
                )
            }
        }
        return emptyList()
    }
}
