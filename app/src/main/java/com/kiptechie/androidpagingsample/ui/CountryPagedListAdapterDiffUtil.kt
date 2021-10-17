package com.kiptechie.androidpagingsample.ui

import androidx.recyclerview.widget.DiffUtil
import com.kiptechie.androidpagingsample.data.models.Country
import java.util.*

class CountryPagedListAdapterDiffUtil : DiffUtil.ItemCallback<Country>() {

    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return Objects.equals(oldItem, newItem)
    }
}