package pl.qpony.currencyapp.data.repository

import pl.qpony.currencyapp.data.api.CurrencyApi
import pl.qpony.currencyapp.data.model.Currency
import pl.qpony.currencyapp.domain.CurrencyRepository
import retrofit2.Response
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val currencyApi: CurrencyApi) : CurrencyRepository {

    override suspend fun getRatesByDate(): Response<Currency> {
        return currencyApi.getRatesByDate()
    }

    override suspend fun getLatestRates(): Response<Currency> {
        return currencyApi.getLatestRates()
    }
}
