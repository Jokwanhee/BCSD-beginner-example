package com.example.chapter12.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Word::class], version = 2, exportSchema = true)
abstract class AppDatabase:RoomDatabase() {
    abstract fun wordDao():WordDao

    // 싱글톤 패턴으로 진행한다.
    // 이유는? AppDataBase 의 인스턴스는 메모리를 많이 소비한다.
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-database.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}

