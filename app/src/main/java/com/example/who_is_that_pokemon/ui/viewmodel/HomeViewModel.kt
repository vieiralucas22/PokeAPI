package com.example.who_is_that_pokemon.ui.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.example.who_is_that_pokemon.model.entity.Pokemon
import com.example.who_is_that_pokemon.model.repository.remote.PokemonRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val pokemonRepository = PokemonRepository(application)

    private val _displayedPokemon = MutableLiveData(emptyList<Pokemon>())
    val displayedPokemon: LiveData<List<Pokemon>> = _displayedPokemon

    init {
        loadPokemon()
    }

    fun loadPokemon()
    {
        try {
            viewModelScope.launch {
                val response = pokemonRepository.getInitialPokemon()

                if (response.isSuccessful && response.body() != null)
                {
                    val body = response.body()

                    if (body != null && body.pokemons.isNotEmpty()) {
                        _displayedPokemon.value = body.pokemons
                    }
                }

            }
        } catch (e : Exception)
        {
            Toast.makeText(application, e.message, Toast.LENGTH_LONG).show()
        }
    }

}