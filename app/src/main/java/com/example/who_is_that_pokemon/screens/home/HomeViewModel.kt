package com.example.who_is_that_pokemon.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.who_is_that_pokemon.constants.RetrofitConstants
import com.example.who_is_that_pokemon.data.dto.PokemonDTO
import com.example.who_is_that_pokemon.data.dto.SpritesDTO
import com.example.who_is_that_pokemon.domain.repository.IPokemonRepository
import com.example.who_is_that_pokemon.screens.common.BaseViewModel
import com.example.who_is_that_pokemon.screens.home.model.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val _pokemonRepository: IPokemonRepository
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState.Loading)

    val uiState: StateFlow<HomeUIState> = _uiState.onStart {
        loadPokemon()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        initialValue = _uiState.value
    )

    private val _displayedPokemonDTO = MutableLiveData(emptyList<PokemonDTO>())
    val displayedPokemonDTO: LiveData<List<PokemonDTO>> = _displayedPokemonDTO

    private var nextPokemon: String = ""

    private var pokemonDTOInScreen: MutableList<PokemonDTO> = mutableListOf()

    fun loadPokemon() {

        viewModelScope.launch {
            try {
                val response = _pokemonRepository.getInitialPokemon()

                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()

                    if (body != null && body.pokemonDTOS.isNotEmpty()) {
                        fillAllPokemonInfo(body.pokemonDTOS)
                        nextPokemon = body.next20Pokemons
                    }
                }
            } catch (e: Exception) {
                // Toast.makeText(application, e.message, Toast.LENGTH_LONG).show()
            } finally {
                isLoading = false
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

    fun fillAllPokemonInfo(allPokemonDTO: List<PokemonDTO>) {
        viewModelScope.launch {
            for (pokemon in allPokemonDTO) {
                val response = _pokemonRepository.getPokemonByNameOrId(pokemon.name)

                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()

                    fillPokemonInfo(pokemon, body)
                }
            }

            updatePokemonDisplayed(allPokemonDTO)
        }
    }

    suspend fun fillPokemonInfo(newPokemonDTO: PokemonDTO, body: PokemonDTO?) {
        newPokemonDTO.weight = body?.weight!!
        newPokemonDTO.height = body.height
        newPokemonDTO.id = body.id
        fillAllPokemonStats(newPokemonDTO, body)
        fillPokemonSprites(newPokemonDTO, body)
        fillPokemonTypes(newPokemonDTO, body)
        fillPokemonColor(newPokemonDTO)
    }

    fun fillAllPokemonStats(newPokemonDTO: PokemonDTO, body: PokemonDTO?) {
        if (body != null && body.stats.isNotEmpty())
            newPokemonDTO.stats = body.stats
    }

    fun fillPokemonSprites(newPokemonDTO: PokemonDTO, body: PokemonDTO?) {
        if (body != null) {
            newPokemonDTO.spritesDTO = SpritesDTO()
            newPokemonDTO.spritesDTO.default = body.spritesDTO.default
            newPokemonDTO.spritesDTO.shiny = body.spritesDTO.shiny
        }
    }

    fun fillPokemonTypes(newPokemonDTO: PokemonDTO, body: PokemonDTO?) {
        if (body != null && body.types.isNotEmpty())
            newPokemonDTO.types = body.types
    }

    fun getNext20PokemonInfo(): Pair<Int, Int> {
        val query =
            nextPokemon.replace(RetrofitConstants.Companion.BASE_POKE_API_URL, "")
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

    fun updatePokemonDisplayed(newPokemonDTOS: List<PokemonDTO>) {
        for (pokemon in newPokemonDTOS) {
            pokemonDTOInScreen.add(pokemon)
        }

        _displayedPokemonDTO.value = pokemonDTOInScreen.toList()
    }

}