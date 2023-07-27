package com.example.chapter13.practice.di

import android.content.Context
import com.example.chapter13.practice.db.WordDao
import com.example.chapter13.practice.db.WordLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext context: Context, wordDao: WordDao): WordLocalDataSource {
        return WordLocalDataSource(context, wordDao)
    }
}