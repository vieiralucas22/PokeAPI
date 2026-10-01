package com.example.who_is_that_pokemon.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.who_is_that_pokemon.constants.RetrofitConstants
import com.example.who_is_that_pokemon.domain.usecase.LoadPokemonUseCase
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
    private val _loadPokemonUseCase: LoadPokemonUseCase
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

            val data = _loadPokemonUseCase.invoke(offset, limit)

            try {
                _uiState.update {
                    if (data != null) {
                        val previousPokemon = (it as? HomeUIState.Success)?.data?.allPokemon.orEmpty()
                        val successData = data.copy(allPokemon = previousPokemon + data.allPokemon)

                        HomeUIState.Success(successData)
                    } else
                        HomeUIState.Error("Pokemon not found!")
                }

            } catch (_: Exception) {
                HomeUIState.Error("Unknown error!")
            }
        }
    }

    private fun getNext20PokemonInfo(next20Pokemon: String?): Pair<Int, Int> {

        if (next20Pokemon.isNullOrBlank()) return 0 to 20

        val query =
            next20Pokemon.replace(RetrofitConstants.BASE_POKE_API_URL, "")
                .substringAfter("?", "")
        val params = query.split("&")
            .associate {
                val (key, value) = it.split("=")
                key to value
            }

        val offset = params["offset"]?.toIntOrNull() ?: 0
        val limit = params["limit"]?.toIntOrNull() ?: 20

        return offset to limit
    }

}