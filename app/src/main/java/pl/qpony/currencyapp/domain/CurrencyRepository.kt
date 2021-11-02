package pl.qpony.currencyapp.domain

import pl.qpony.currencyapp.data.model.Currency
import retrofit2.Response

interface CurrencyRepository {
    suspend fun getRatesByDate(): Response<Currency>
    suspend fun getLatestRates(): Response<Currency>
}