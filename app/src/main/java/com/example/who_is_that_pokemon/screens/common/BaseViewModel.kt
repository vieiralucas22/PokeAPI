package com.example.who_is_that_pokemon.screens.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.who_is_that_pokemon.data.dto.PokemonDTO

abstract class BaseViewModel : ViewModel() {

    var isLoading by mutableStateOf(false)

    protected suspend fun fillPokemonColor(pokemonDTO: PokemonDTO) {
//        val pokemonColor = pokemonRepository.getPokemonSpecieByName(pokemon.name)

//        pokemon.color = when (pokemonColor?.pokemonColor?.colorName) {
//            "red" -> PokemonRed
//            "blue" -> PokemonBlue
//            "yellow" -> PokemonYellow
//            "green" -> PokemonGreen
//            "black" -> PokemonBlack
//            "white" -> PokemonWhite
//            "gray" -> PokemonGray
//            "pink" -> PokemonPink
//            "purple" -> PokemonPurple
//            "brown" -> PokemonBrown
//            else -> PokemonDefault
//        }
    }

}