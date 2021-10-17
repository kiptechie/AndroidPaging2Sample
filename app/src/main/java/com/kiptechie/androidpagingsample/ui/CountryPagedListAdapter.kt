package com.kiptechie.androidpagingsample.ui

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncDifferConfig
import com.kiptechie.androidpagingsample.data.models.Country

class CountryPagedListAdapter(config: AsyncDifferConfig<Country>) :
    PagedListAdapter<Country, CountryViewHolder>(config) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = getItem(position)
        if (country != null) {
            holder.bind(country)
        }
    }
}