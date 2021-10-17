package com.kiptechie.androidpagingsample.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiptechie.androidpagingsample.databinding.ActivityMainBinding
import com.kiptechie.androidpagingsample.repository.CountriesDb

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        CountriesDb.initialize(this)

        val countryPagedListAdapterDiffUtil = CountryPagedListAdapterDiffUtil()
        val asyncPagedListAdapterDiffUtil =
            AsyncDifferConfig.Builder(countryPagedListAdapterDiffUtil).build()
        val countriesPagedAdapter = CountryPagedListAdapter(asyncPagedListAdapterDiffUtil)
        setUpRecycler(countriesPagedAdapter)
    }

    private fun setUpRecycler(countriesPagedAdapter: CountryPagedListAdapter) {
        binding.pagingRv.layoutManager = LinearLayoutManager(this)
        binding.pagingRv.adapter = countriesPagedAdapter
        setUpObservers(countriesPagedAdapter)
    }

    private fun setUpObservers(countriesPagedAdapter: CountryPagedListAdapter) {
        mainViewModel.countries.observe(this, { countries ->
            Log.v(TAG, "Observed ${countries.size} countries from viewModel...")
            countriesPagedAdapter.submitList(countries)
        })
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.deleteTop.setOnClickListener {
            mainViewModel.deleteTop()
        }
    }
}