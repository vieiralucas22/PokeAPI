package com.example.who_is_that_pokemon.screens.pokemondetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.who_is_that_pokemon.data.entity.Stats
import com.example.who_is_that_pokemon.data.entity.TypeSlot
import com.example.who_is_that_pokemon.domain.repository.IPokemonRepository
import com.example.who_is_that_pokemon.screens.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val _pokemonRepository: IPokemonRepository
) : BaseViewModel() {

    var id by mutableIntStateOf(0)
    var pokemonName by mutableStateOf("")
    var description by mutableStateOf("")
    var sprite by mutableStateOf("")
    var color by mutableStateOf(Color(0xFFFFFFFF))
    var shouldShowNotFoundComponent by mutableStateOf(false)
    private val _pokemonStats = MutableLiveData(emptyList<Stats>())
    val pokemonStats: LiveData<List<Stats>> = _pokemonStats

    private val _pokemonTypes = MutableLiveData(emptyList<TypeSlot>())

    val pokemonTypes: LiveData<List<TypeSlot>> = _pokemonTypes

    private var currentPokemonName = ""

    fun loadPokemonInformation() {
        if (currentPokemonName.isEmpty()) {
            shouldShowNotFoundComponent = true
            return
        }

        if (isLoading) return

        isLoading = true

        viewModelScope.launch {

            try {
                val response = _pokemonRepository.getPokemonByNameOrId(currentPokemonName)

                if (response.isSuccessful && response.body() != null) {
                    val specie = _pokemonRepository.getPokemonSpecieByName(currentPokemonName)
                    val pokemon = response.body()
                    if (specie != null && pokemon != null) {
                        fillPokemonColor(pokemon)

                        id = pokemon.id
                        pokemonName = pokemon.name
                       // description = specie.descriptions[0].text
                        sprite = pokemon.sprites.default
                        color = pokemon.color
                        _pokemonStats.value = pokemon.stats
                        _pokemonTypes.value = pokemon.types
                        shouldShowNotFoundComponent = false
                    }
                } else {
                    shouldShowNotFoundComponent = true
                }
            } catch (e: Exception) {
                shouldShowNotFoundComponent = true
               // Toast.makeText(application, e.message, Toast.LENGTH_LONG).show()
            } finally {
                isLoading = false
            }
        }
    }

    fun getTypeColor(color: String): Color = Color(0xFFFFFFFF)

    fun setCurrentPokemonName(pokemonName: String) {
        currentPokemonName = pokemonName
    }

    fun clearPokemonInfo() {
        isLoading = false
        pokemonName = ""
        description = ""
        sprite = ""
        color = Color(0xFFFFFFFF)
        _pokemonStats.value = emptyList()
        _pokemonTypes.value = emptyList()
        shouldShowNotFoundComponent = false
    }
}