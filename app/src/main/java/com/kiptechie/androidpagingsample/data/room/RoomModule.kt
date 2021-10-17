package com.kiptechie.androidpagingsample.data.room

import android.content.Context
import androidx.room.Room

object RoomModule {

    private var db: AppDatabase? = null
    private const val DATABASE_NAME = "app_db"

    fun getInstance(context: Context): AppDatabase {
        if (db == null) {
            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
        return db as AppDatabase
    }
}