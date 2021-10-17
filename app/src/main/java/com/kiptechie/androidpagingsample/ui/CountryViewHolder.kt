package com.kiptechie.androidpagingsample.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kiptechie.androidpagingsample.R
import com.kiptechie.androidpagingsample.data.models.Country

class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val TAG = CountryViewHolder::class.java.simpleName

    private val countryTitleTv = view.findViewById<TextView>(R.id.countryNameTv)
    private val countryPopulationTv = view.findViewById<TextView>(R.id.countryPopulationTv)

    @SuppressLint("SetTextI18n")
    fun bind(country: Country) {
        countryTitleTv.text = "(${country.countryCode}) ${country.name}"
        countryPopulationTv.text = "${country.population} people"
    }

    companion object {
        fun create(parent: ViewGroup): CountryViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
            return CountryViewHolder(view)
        }
    }
}