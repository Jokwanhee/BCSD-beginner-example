package com.example.chapter13.practice.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao

//    companion object {
//        private var INSTANCE: AppDatabase? = null
//        fun getInstance(context:Context): AppDatabase? {
//            if (INSTANCE == null) {
//                synchronized(AppDatabase::class.java) {
//                    INSTANCE = Room.databaseBuilder(
//                        context.applicationContext,
//                        AppDatabase::class.java,
//                        "app-database.db"
//                    ).build()
//                }
//            }
//            return INSTANCE
//        }
//    }
}