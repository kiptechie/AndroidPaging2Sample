package com.kiptechie.androidpagingsample.data.sources

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.kiptechie.androidpagingsample.data.models.Country
import com.kiptechie.androidpagingsample.repository.CountriesDb

class PagedCountriesDataSource : PageKeyedDataSource<Int, Country>() {

    private val TAG = PagedCountriesDataSource::class.java.simpleName
    private var source = CountriesDb.getCountries()

    fun deleteById(id: Int) {
        Log.v(TAG, "removing country by id $id and invalidating...")
        CountriesDb.deleteCountryById(id)
        invalidate()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Country>
    ) {
        Log.v(TAG, "loadInitial called")
        val pagedCountries = source.filter { country: Country -> country.page == 1 }
        Log.v(TAG, "loadInitial created a list of ${pagedCountries.size} size...")

        callback.onResult(pagedCountries, null, 2)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Country>) {
        Log.v(TAG, "loadBefore called with key ${params.key}")
        val list = source.filter { country: Country -> country.page == params.key }
        Log.v(TAG, "loadBefore returning list for page ${params.key} with ${list.size} items...")

        callback.onResult(list, params.key - 1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Country>) {
        Log.v(TAG, "loadAfter called with key ${params.key}")
        val list = source.filter { country: Country -> country.page == params.key }
        Log.v(TAG, "loadAfter returning list for page ${params.key} with ${list.size} items...")

        callback.onResult(list, params.key + 1)
    }
}

class PagedCountriesDataSourceFactory : DataSource.Factory<Int, Country>() {

    var dataSource = MutableLiveData<PagedCountriesDataSource>()
    lateinit var latestSource: PagedCountriesDataSource
    private val TAG = PagedCountriesDataSourceFactory::class.java.simpleName

    override fun create(): DataSource<Int, Country> {
        latestSource = PagedCountriesDataSource()
        dataSource.postValue(latestSource)
        return latestSource
    }

    fun deleteById(id: Int) {
        Log.v(TAG, "removing country by id ${id}...")
        latestSource.deleteById(id)
    }

}