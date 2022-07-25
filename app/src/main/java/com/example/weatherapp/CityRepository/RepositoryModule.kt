package com.example.weatherapp.CityRepository

import com.example.weatherapp.network.CityApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyRepo(api:CityApiService): MyRepository {
        return CityRepository(api)
    }
}
*/

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMyRepository(
        repo: CityRepository
    ): MyRepository
}

