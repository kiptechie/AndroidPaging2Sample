package com.kiptechie.androidpagingsample.data.sources

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.kiptechie.androidpagingsample.data.models.Country
import com.kiptechie.androidpagingsample.repository.CountriesDb

class PositionalCountryDataSource : PositionalDataSource<Country>() {

    private val TAG = PositionalCountryDataSource::class.java.simpleName
    private val source = CountriesDb.getCountries()
    private var batchSize = 0

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Country>) {
        Log.v(TAG, "loadInitial called")
        batchSize = params.requestedLoadSize

        val list = mutableListOf<Country>()
        for (i in params.requestedStartPosition..params.requestedLoadSize) {
            if (i == source.size) {
                break
            }
            list.add(source[i])
        }

        Log.v(TAG, "loadInitial created a list of ${list.size} items...")

        callback.onResult(list, params.requestedStartPosition, list.size)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Country>) {
        Log.v(TAG, "loadRange called")
        val list = mutableListOf<Country>()
        for (i in params.startPosition..(params.startPosition + batchSize)) {
            if (i == source.size) {
                break
            }
            list.add(source[i])
        }

        Log.v(TAG, "loadRange created a list of ${list.size} items...")

        callback.onResult(list)
    }
}

class PositionalCountryDataSourceFactory : DataSource.Factory<Int, Country>() {
    var dataSource = MutableLiveData<PositionalCountryDataSource>()
    lateinit var latestSource: PositionalCountryDataSource

    override fun create(): DataSource<Int, Country> {
        latestSource = PositionalCountryDataSource()
        dataSource.postValue(latestSource)

        return latestSource
    }
}