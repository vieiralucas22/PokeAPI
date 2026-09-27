package com.example.who_is_that_pokemon.di

import com.example.who_is_that_pokemon.domain.repository.PokemonRepositoryImpl
import com.example.who_is_that_pokemon.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsPokemonRepository(impl: PokemonRepositoryImpl): PokemonRepository

}