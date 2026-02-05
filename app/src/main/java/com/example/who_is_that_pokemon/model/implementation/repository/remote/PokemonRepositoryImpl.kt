package com.example.who_is_that_pokemon.model.implementation.repository.remote

import android.app.Application
import com.example.who_is_that_pokemon.exception.NoInternetException
import com.example.who_is_that_pokemon.model.entity.InitialPokemonResponse
import com.example.who_is_that_pokemon.model.entity.Pokemon
import com.example.who_is_that_pokemon.model.entity.SpecieDetails
import com.example.who_is_that_pokemon.model.interfaces.repository.remote.IPokemonRepository
import com.example.who_is_that_pokemon.model.repository.remote.BaseRepository
import com.example.who_is_that_pokemon.model.repository.remote.service.PokemonService
import retrofit2.Response
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    application: Application,
    private val _pokemonService: PokemonService
) : BaseRepository(application), IPokemonRepository {

    override suspend fun getInitialPokemon(): Response<InitialPokemonResponse> {
        return safeApiCall {
            _pokemonService.getSomePokemon()
        }
    }

    override suspend fun getPokemonByNameOrId(name: String): Response<Pokemon> {
        return safeApiCall {
            _pokemonService.getPokemonByNameOrId(name)
        }
    }

    override suspend fun getPokemonSpecieByName(name: String): SpecieDetails? {
        if (!isConnectionAvailable()) {
            throw NoInternetException("No internet available")
        }

        val response = _pokemonService.getPokemonSpecieByName(name)

        if (response.isSuccessful && response.body() != null) {
            val specie = response.body()

            if (specie?.pokemonColor == null)
                return null

            return specie
        }

        return null
    }

    override suspend fun getNext20Pokemon(
        offset: Int,
        limit: Int
    ): Response<InitialPokemonResponse> {
        return safeApiCall {
            _pokemonService.getNext20Pokemon(offset, limit)
        }
    }
}