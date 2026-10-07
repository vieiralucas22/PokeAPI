package com.example.who_is_that_pokemon.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.who_is_that_pokemon.domain.usecase.LoadPokemonUseCase
import com.example.who_is_that_pokemon.common.helpers.RetrofitHelper.Companion.getNext20PokemonInfo
import com.example.who_is_that_pokemon.screens.home.model.HomeUIState
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
class HomeViewModel @Inject constructor(
    private val loadPokemonUseCase: LoadPokemonUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState.Loading)

    val uiState: StateFlow<HomeUIState> = _uiState.onStart {
        loadPokemon()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        initialValue = _uiState.value
    )

    fun loadPokemon(nextPokemon: String? = null) {
        viewModelScope.launch {
            val (offset, limit) = getNext20PokemonInfo(nextPokemon)

            val data = loadPokemonUseCase(offset, limit)

            try {
                _uiState.update {
                    if (data != null) {
                        val previousPokemonList = (it as? HomeUIState.Success)?.data?.allPokemon.orEmpty()
                        val successData = data.copy(allPokemon = previousPokemonList + data.allPokemon)

                        HomeUIState.Success(successData)
                    } else
                        HomeUIState.Error("Pokemon not found!")
                }

            } catch (_: Exception) {
                HomeUIState.Error("Unknown error!")
            }
        }
    }

}