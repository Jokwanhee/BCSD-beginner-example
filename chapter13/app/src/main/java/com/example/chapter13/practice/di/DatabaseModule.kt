package com.example.chapter13.practice.di

import android.content.Context
import androidx.room.Room
import com.example.chapter13.practice.db.AppDatabase
import com.example.chapter13.practice.db.WordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app-database.db"
        )
        .allowMainThreadQueries()
        .build()
    }
    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase): WordDao {
        return appDatabase.wordDao()
    }
}