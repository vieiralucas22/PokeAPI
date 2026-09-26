package com.example.who_is_that_pokemon.screens.common

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.who_is_that_pokemon.data.entity.Pokemon

abstract class BaseViewModel (
    application: Application,
) : AndroidViewModel(application) {

    var isLoading by mutableStateOf(false)

    protected suspend fun fillPokemonColor(pokemon: Pokemon) {
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