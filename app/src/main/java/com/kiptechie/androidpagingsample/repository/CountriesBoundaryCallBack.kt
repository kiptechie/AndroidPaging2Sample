package com.kiptechie.androidpagingsample.repository

import android.util.Log
import androidx.paging.PagedList
import com.blankj.utilcode.util.ToastUtils
import com.kiptechie.androidpagingsample.data.models.Country

class CountriesBoundaryCallBack : PagedList.BoundaryCallback<Country>() {

    private val TAG = CountriesBoundaryCallBack::class.java.simpleName

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        Log.i(TAG, "onZeroItemsLoaded")
    }

    override fun onItemAtFrontLoaded(itemAtFront: Country) {
        super.onItemAtFrontLoaded(itemAtFront)
        Log.i(TAG, "onItemAtFrontLoaded")
    }

    override fun onItemAtEndLoaded(itemAtEnd: Country) {
        super.onItemAtEndLoaded(itemAtEnd)
        Log.i(TAG, "onItemAtEndLoaded")
        ToastUtils.showShort("No more Items!")
    }
}