package com.example.who_is_that_pokemon.screens.pokemondetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.who_is_that_pokemon.domain.usecase.FindPokemonUseCase
import com.example.who_is_that_pokemon.screens.pokemondetails.model.PokemonDetailsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val _findPokemonUseCase: FindPokemonUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<PokemonDetailsUIState> = MutableStateFlow(PokemonDetailsUIState.Loading)
    val uiState: StateFlow<PokemonDetailsUIState> = _uiState.onStart {
        loadPokemonInformation()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = _uiState.value
    )

    private var currentPokemonKey = ""

    fun loadPokemonInformation() {

        viewModelScope.launch {

            try {
                val data = _findPokemonUseCase.invoke(currentPokemonKey)

                _uiState.update {
                    if (data != null)
                        PokemonDetailsUIState.Success(data)
                    else
                        PokemonDetailsUIState.Error("No pokemon")
                }

            } catch (_: Exception) {
                PokemonDetailsUIState.Error("No pokemon error")
            }
        }
    }

    fun setCurrentPokemonKey(pokemonKey: String) {
        currentPokemonKey = pokemonKey
    }
}