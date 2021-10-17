package com.kiptechie.androidpagingsample.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kiptechie.androidpagingsample.data.models.Country

@Database(
    version = 1,
    entities = [Country::class]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao

}