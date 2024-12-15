package com.plcoding.stockmarketapp.presentation.company_listings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.stockmarketapp.domain.model.CompanyListing
import com.plcoding.stockmarketapp.domain.repository.StockRepository
import com.plcoding.stockmarketapp.util.Resource
import com.plcoding.stockmarketapp.util.printLn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

object TemporaryData {
    val data = listOf(
        CompanyListing(name = "MyCompany", symbol = "myc", exchange = "10"),
        CompanyListing(name = "YourCompany", symbol = "myc", exchange = "10"),
        CompanyListing(name = "OwrCompany", symbol = "myc", exchange = "10"),
        CompanyListing(name = "TheirCompany", symbol = "myc", exchange = "10"),
        CompanyListing(name = "WhatEverMyCompany", symbol = "myc", exchange = "10"),
    )
}

@HiltViewModel
class CompanyListingsViewModel @Inject constructor(
    private val repository: StockRepository
): ViewModel() {
    var state by mutableStateOf(CompanyListingsState())
    init {
        getCompanyListings()
    }


    /**
     * This function is made to fetch the research made by the user
     * The default query is ""
     * Let's just forgot the query and find out how the data is fetched
     */
    private fun getCompanyListings() {
        viewModelScope.launch {
            repository
                .getCompanyListings(false, "")
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                printLn("{getCompanyListing}" +
                                        "successfully loaded")
                                state = state.copy(
                                    companies = listings
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}