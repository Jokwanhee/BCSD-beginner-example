package com.example.chapter13.practice.di

import com.example.chapter13.practice.db.WordLocalDataSource
import com.example.chapter13.practice.repository.WordRepository
import com.example.chapter13.practice.repository.WordRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideWordRepository(
        wordLocalDataSource: WordLocalDataSource
    ): WordRepository {
        return WordRepositoryImpl(wordLocalDataSource)
    }
}
