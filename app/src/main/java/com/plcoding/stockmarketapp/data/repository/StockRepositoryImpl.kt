package com.plcoding.stockmarketapp.data.repository

import androidx.compose.material.Text
import com.opencsv.CSVReader
import com.plcoding.stockmarketapp.data.csv.CSVParser
import com.plcoding.stockmarketapp.data.csv.CompanyListingsParser
import com.plcoding.stockmarketapp.data.local.StockDatabase
import com.plcoding.stockmarketapp.data.mapper.toCompanyListing
import com.plcoding.stockmarketapp.domain.model.CompanyListing
import com.plcoding.stockmarketapp.domain.repository.StockRepository
import com.plcoding.stockmarketapp.presentation.company_listings.TemporaryData
import com.plcoding.stockmarketapp.util.Resource
import com.plcoding.stockmarketapp.util.printLn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val db: StockDatabase,
): StockRepository {

    private val dao = db.dao

    /**
     * The class StockRepositoryImpl fetches the list of companies
     * from the database, than send a flow to VM with the complete list list within
     */

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {

          // it fetches the list from the data base
            val localListings = dao.searchCompanyListing(query)

            emit(Resource.Success(
                data = localListings.map { it.toCompanyListing() }
            ))

        }
    }
}