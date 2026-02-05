package com.example.who_is_that_pokemon.di

import com.example.who_is_that_pokemon.model.implementation.repository.remote.PokemonRepositoryImpl
import com.example.who_is_that_pokemon.model.interfaces.repository.remote.IPokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsPokemonRepository(impl : PokemonRepositoryImpl) : IPokemonRepository

}