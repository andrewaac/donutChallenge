package com.andrewaac.donutchallenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
class DispatchersModule {

    @Provides
    @Named("main")
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Named("io")
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Named("default")
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
