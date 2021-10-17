package com.kiptechie.androidpagingsample.ui

import android.view.View
import androidx.recyclerview.widget.AsyncDifferConfig
import com.kiptechie.androidpagingsample.R
import com.kiptechie.androidpagingsample.repository.CountriesDb

class OnDataSourceChosen(private val mainActivity: MainActivity) : OnDataSourceChosenListener {
    override fun onDataChosen(isRoom: Boolean) {
        mainActivity.isRoom = isRoom
        mainActivity.binding.buttonsLl.visibility = View.GONE
        mainActivity.binding.pagingRv.visibility = View.VISIBLE
        mainActivity.binding.deleteTop.visibility = View.VISIBLE
        if (!isRoom) {
            mainActivity.supportActionBar?.title = mainActivity.getString(R.string.app_name_json)
        } else {
            mainActivity.supportActionBar?.title = mainActivity.getString(R.string.app_name_room)
        }

        CountriesDb.initialize(mainActivity, isRoom)

        val countryPagedListAdapterDiffUtil = CountryPagedListAdapterDiffUtil()
        val asyncPagedListAdapterDiffUtil =
            AsyncDifferConfig.Builder(countryPagedListAdapterDiffUtil).build()
        val countriesPagedAdapter = CountryPagedListAdapter(asyncPagedListAdapterDiffUtil)
        mainActivity.setUpRecycler(countriesPagedAdapter)
    }
}