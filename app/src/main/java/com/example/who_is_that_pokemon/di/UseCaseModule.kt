package com.example.who_is_that_pokemon.di

import com.example.who_is_that_pokemon.domain.usecase.LoadPokemonUseCase
import com.example.who_is_that_pokemon.domain.usecase.LoadPokemonUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindsLoadAllPokemonUseCase(impl: LoadPokemonUseCaseImpl) : LoadPokemonUseCase
}