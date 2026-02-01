package com.example.who_is_that_pokemon.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.who_is_that_pokemon.model.entity.Pokemon
import com.example.who_is_that_pokemon.model.repository.remote.PokemonRepository
import com.example.who_is_that_pokemon.ui.theme.PokemonBlack
import com.example.who_is_that_pokemon.ui.theme.PokemonBlue
import com.example.who_is_that_pokemon.ui.theme.PokemonBrown
import com.example.who_is_that_pokemon.ui.theme.PokemonDefault
import com.example.who_is_that_pokemon.ui.theme.PokemonGray
import com.example.who_is_that_pokemon.ui.theme.PokemonGreen
import com.example.who_is_that_pokemon.ui.theme.PokemonPink
import com.example.who_is_that_pokemon.ui.theme.PokemonPurple
import com.example.who_is_that_pokemon.ui.theme.PokemonRed
import com.example.who_is_that_pokemon.ui.theme.PokemonWhite
import com.example.who_is_that_pokemon.ui.theme.PokemonYellow
import kotlinx.coroutines.launch

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected val pokemonRepository = PokemonRepository(application)

    var isLoading by mutableStateOf(false)

    protected suspend fun fillPokemonColor(pokemon: Pokemon) {
            val pokemonColor = pokemonRepository.getPokemonSpecieByName(pokemon.name)

            pokemon.color = when (pokemonColor?.pokemonColor?.colorName) {
                "red" -> PokemonRed
                "blue" -> PokemonBlue
                "yellow" -> PokemonYellow
                "green" -> PokemonGreen
                "black" -> PokemonBlack
                "white" -> PokemonWhite
                "gray" -> PokemonGray
                "pink" -> PokemonPink
                "purple" -> PokemonPurple
                "brown" -> PokemonBrown
                else -> PokemonDefault
        }
    }

    fun delay(milliseconds : Long) {
        viewModelScope.launch {
            delay(milliseconds)
        }
    }

}