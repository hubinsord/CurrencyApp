package pl.qpony.currencyapp.domain

import pl.qpony.currencyapp.data.model.CurrencyResponse
import retrofit2.Response

interface CurrencyRepository {
    suspend fun getRatesByDate(): Response<CurrencyResponse>
    suspend fun getLatestRates(): Response<CurrencyResponse>
}