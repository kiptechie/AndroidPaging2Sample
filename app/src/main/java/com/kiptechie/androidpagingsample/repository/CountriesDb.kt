package com.kiptechie.androidpagingsample.repository

import android.content.Context
import com.google.gson.Gson
import com.kiptechie.androidpagingsample.data.models.Country

class CountriesDb {

    companion object {

        private lateinit var countries: ArrayList<Country>

        fun initialize(context: Context) {
            val content = context.assets.open("countries_paged.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
            countries = ArrayList(
                Gson().fromJson(content, Array<Country>::class.java)
                    .toList()
            )
        }

        fun getCountries(): List<Country> {
            return countries
        }

        fun deleteCountry(countryCode: String) {
            countries.filter { countryCode != countryCode }
        }

        fun addCountry(country: Country) {
            countries.add(country)
        }

    }
}