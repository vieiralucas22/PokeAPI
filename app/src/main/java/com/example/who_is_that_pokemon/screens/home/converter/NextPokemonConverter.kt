package com.example.who_is_that_pokemon.screens.home.converter

import com.example.who_is_that_pokemon.data.dto.InitialPokemonDTO
import com.example.who_is_that_pokemon.data.dto.PokemonDTO
import com.example.who_is_that_pokemon.data.dto.PokemonDescriptionDTO
import com.example.who_is_that_pokemon.data.dto.StatsDTO
import com.example.who_is_that_pokemon.data.dto.TypeSlotDTO
import com.example.who_is_that_pokemon.screens.common.model.InitialPokemonState
import com.example.who_is_that_pokemon.screens.common.model.PokemonColorState
import com.example.who_is_that_pokemon.screens.common.model.PokemonDescriptionState
import com.example.who_is_that_pokemon.screens.common.model.PokemonState
import com.example.who_is_that_pokemon.screens.common.model.SpecieDetailsState
import com.example.who_is_that_pokemon.screens.common.model.SpritesState
import com.example.who_is_that_pokemon.screens.common.model.StatState
import com.example.who_is_that_pokemon.screens.common.model.StatsState
import com.example.who_is_that_pokemon.screens.common.model.TypeSlotState
import com.example.who_is_that_pokemon.screens.common.model.TypeState
import javax.inject.Inject

class NextPokemonConverter @Inject constructor() {
    fun convert(dto: InitialPokemonDTO): InitialPokemonState =
        InitialPokemonState(
            total = dto.total,
            allPokemon = buildAllPokemon(dto.allPokemon),
            next20Pokemon = dto.next20Pokemon
        )

    fun buildAllPokemon(nextPokemon: List<PokemonDTO>): List<PokemonState> =
        nextPokemon.map { pokemonDTO ->
            PokemonState(
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
                    pokemonColor = PokemonColorState(
                        pokemonDTO.specieDetails?.pokemonColor?.colorName ?: ""
                    ),
                    descriptions = buildPokemonDescription(pokemonDTO.specieDetails?.descriptions)
                )
            )
        }

    fun buildPokemonTypes(typesDTO: List<TypeSlotDTO>?): List<TypeSlotState> {

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

    fun buildPokemonStats(statsDTO: List<StatsDTO>?): List<StatsState> {
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
