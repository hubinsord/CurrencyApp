package pl.qpony.currencyapp.data.api

import pl.qpony.currencyapp.data.model.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApi {
    @GET("{date}")
    suspend fun getRatesByDate(@Path("date") date: String): Response<CurrencyResponse>

    @GET("latest")
    suspend fun getLatestRates(): Response<CurrencyResponse>
}