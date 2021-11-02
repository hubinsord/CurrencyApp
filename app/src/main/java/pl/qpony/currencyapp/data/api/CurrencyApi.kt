package pl.qpony.currencyapp.data.api

import pl.qpony.currencyapp.Constants
import pl.qpony.currencyapp.data.model.Currency
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {


    @GET("2013-12-24")
    suspend fun getRatesByDate(
        @Query("access_key")
        accessKey: String = Constants.FIXER_API_ACCESS_KEY,
    ): Response<Currency>

    @GET("latest")
    suspend fun getLatestRates(
        @Query("access_key")
        accessKey: String = Constants.FIXER_API_ACCESS_KEY
    ): Response<Currency>
}