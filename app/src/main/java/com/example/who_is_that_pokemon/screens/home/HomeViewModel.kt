package com.example.who_is_that_pokemon.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun loadPokemon() {
        viewModelScope.launch {
            val data = _loadPokemonUseCase.invoke()

            try {
                _uiState.update {
                    if (data != null)
                        HomeUIState.Success(data)
                    else
                        HomeUIState.Error("Pokemon not found!")
                }

            } catch (_: Exception) {
                HomeUIState.Error("Unknown error!")
            }
        }
    }

//    fun loadNext20Pokemon() {
//        if (isLoading) return
//
//        isLoading = true
//
//        viewModelScope.launch {
//            try {
//
//                val (offset, limit) = getNext20PokemonInfo()
//
//                val response = _pokemonRepository.getNext20Pokemon(offset, limit)
//
//                if (response.isSuccessful && response.body() != null) {
//                    val body = response.body()
//
//                    if (body != null && body.pokemonDTOS != null && body.pokemonDTOS.isNotEmpty()) {
//                        fillAllPokemonInfo(body.pokemonDTOS)
//                        nextPokemon = body.next20Pokemons
//                    }
//                }
//            } catch (e: Exception) {
//               // Toast.makeText(application, e.message, Toast.LENGTH_LONG).show()
//            } finally {
//                isLoading = false
//            }
//        }
//    }

//    fun getNext20PokemonInfo(): Pair<Int, Int> {
//        val query =
//            nextPokemon.replace(RetrofitConstants.BASE_POKE_API_URL, "")
//                .substringAfter("?", "")
//        val params = query.split("&")
//            .associate {
//                val (key, value) = it.split("=")
//                key to value
//            }
//
//        val offset = params["offset"]?.toIntOrNull() ?: 0
//        val limit = params["limit"]?.toIntOrNull() ?: 20
//
//        return offset to limit
//    }

//    fun updatePokemonDisplayed(newPokemonDTOS: List<PokemonDTO>) {
//        for (pokemon in newPokemonDTOS) {
//            pokemonDTOInScreen.add(pokemon)
//        }
//
//        _displayedPokemonDTO.value = pokemonDTOInScreen.toList()
//    }

}