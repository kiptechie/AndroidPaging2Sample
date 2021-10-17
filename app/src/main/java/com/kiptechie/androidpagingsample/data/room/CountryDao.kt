package com.kiptechie.androidpagingsample.data.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.kiptechie.androidpagingsample.data.models.Country

@Dao
interface CountryDao {

    @Query("SELECT * FROM Country ORDER BY id ASC LIMIT 1")
    fun getTopCountry(): Country

    @Query("SELECT * FROM Country ORDER BY id ASC")
    fun getAllCountries(): List<Country>

    @Query("SELECT * FROM Country WHERE id LIKE :id")
    fun findCountryById(id: Int): Country?

    @Query("SELECT id FROM Country ORDER BY id DESC LIMIT 1")
    fun getLastId(): Int

    @Query("SELECT * FROM Country")
    fun getCountry(): DataSource.Factory<Int, Country>

    @Insert(onConflict = IGNORE)
    fun insert(country: Country)

    @Delete
    fun delete(country: Country)

}