package com.aravind.superops.di

import com.aravind.superops.data.api.ApiService
import com.aravind.superops.data.repository.AuthorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideRepository(apiService: ApiService) : AuthorRepository{
        return AuthorRepository(apiService)
    }
}