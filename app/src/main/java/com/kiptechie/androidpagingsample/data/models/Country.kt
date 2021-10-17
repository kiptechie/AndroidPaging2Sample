package com.kiptechie.androidpagingsample.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Country(
    @PrimaryKey var id: Int,
    var name: String,
    var countryCode: String,
    var shortCode: String,
    var population: String,
    var area: String,
    var page: Int
)
