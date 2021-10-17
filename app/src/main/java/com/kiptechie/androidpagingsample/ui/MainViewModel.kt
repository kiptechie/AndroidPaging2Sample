package com.kiptechie.androidpagingsample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kiptechie.androidpagingsample.data.models.Country
import com.kiptechie.androidpagingsample.data.sources.PagedCountriesDataSourceFactory
import com.kiptechie.androidpagingsample.repository.CountriesBoundaryCallBack

class MainViewModel : ViewModel() {

    private val pageSize = 15
    private var pagingConfig = PagedList.Config.Builder()
        .setPageSize(pageSize)
        .setInitialLoadSizeHint(15)
        .setPrefetchDistance(5)
        .setEnablePlaceholders(false)
        .build()

    private var dataSource = PagedCountriesDataSourceFactory()
    var countries: LiveData<PagedList<Country>> =
        LivePagedListBuilder(dataSource, pagingConfig)
            .setBoundaryCallback(CountriesBoundaryCallBack())
            .build()
}