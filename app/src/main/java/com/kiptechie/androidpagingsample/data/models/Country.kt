package com.kiptechie.androidpagingsample.data.models

data class Country(
    var id: Int,
    var name: String,
    var countryCode: String,
    var shortCode: String,
    var population: String,
    var area: String,
    var page: Int
)
