package pl.qpony.currencyapp.data.api

import pl.qpony.currencyapp.data.model.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyApi {
    @GET("2013-12-24")
    suspend fun getRatesByDate(): Response<CurrencyResponse>

    @GET("latest")
    suspend fun getLatestRates(): Response<CurrencyResponse>
}