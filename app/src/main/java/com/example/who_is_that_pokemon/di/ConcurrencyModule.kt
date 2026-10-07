package com.example.who_is_that_pokemon.di

import com.example.who_is_that_pokemon.common.concurrency.CoroutineDispatcherProvider
import com.example.who_is_that_pokemon.common.concurrency.CoroutineDispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ConcurrencyModule {

    @Binds
    fun bindsCoroutineDispatcherProvider(impl: CoroutineDispatcherProviderImpl): CoroutineDispatcherProvider

}
