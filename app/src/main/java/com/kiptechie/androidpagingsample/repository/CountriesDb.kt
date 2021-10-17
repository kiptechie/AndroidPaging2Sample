package com.kiptechie.androidpagingsample.repository

import android.content.Context
import com.google.gson.Gson
import com.kiptechie.androidpagingsample.data.models.Country
import com.kiptechie.androidpagingsample.data.room.CountryDao
import com.kiptechie.androidpagingsample.data.room.RoomModule
import java.util.concurrent.Executors

class CountriesDb {

    companion object {

        private lateinit var countries: ArrayList<Country>
        private var isRoom = false
        private lateinit var dao: CountryDao

        fun initialize(context: Context, isRoom: Boolean) {
            this.isRoom = isRoom
            val content = context.assets.open("countries_paged.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
            countries = ArrayList(
                Gson().fromJson(content, Array<Country>::class.java)
                    .toList()
            )
            dao = RoomModule.getInstance(context).countryDao()
            if (isRoom) {
                Executors.newCachedThreadPool().submit {
                    for (country in countries) {
                        dao.insert(country)
                    }
                }
            }
        }

        fun getCountries(): List<Country> {
            return if (isRoom) {
                dao.getAllCountries()
            } else {
                countries
            }
        }

        fun deleteCountryById(id: Int) {
            if (isRoom) {
                Executors.newSingleThreadExecutor().submit {
                    val country = dao.findCountryById(id)
                    if (country != null) {
                        dao.delete(country)
                    }
                }
            } else {
                countries.removeAll { country -> country.id == id }
            }
        }

        fun addCountry(country: Country) {
            if (isRoom) {
                Executors.newSingleThreadExecutor().submit {
                    dao.insert(country)
                }
            } else {
                countries.add(country)
            }
        }

    }
}