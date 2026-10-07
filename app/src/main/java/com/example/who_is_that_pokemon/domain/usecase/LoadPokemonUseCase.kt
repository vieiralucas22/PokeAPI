package com.example.who_is_that_pokemon.domain.usecase

import com.example.who_is_that_pokemon.screens.common.model.InitialPokemonState

interface LoadPokemonUseCase {
    suspend operator fun invoke(offset: Int = 0, limit: Int = 20) : InitialPokemonState?
}