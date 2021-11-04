package pl.qpony.currencyapp.ui.currencylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.qpony.currencyapp.data.model.CurrencyResponse
import pl.qpony.currencyapp.domain.CurrencyRepository
import pl.qpony.currencyapp.core.DispatcherProvider
import pl.qpony.currencyapp.data.model.ItemHeaderDate
import pl.qpony.currencyapp.data.model.ItemRowCurrency
import pl.qpony.currencyapp.domain.ItemRecyclerView
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CurrencyListVM @Inject constructor(
    private val repository: CurrencyRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private val _currency = MutableLiveData<CurrencyResponse>()
    val currency: LiveData<CurrencyResponse> get() = _currency

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _viewData = MutableLiveData<List<ItemRecyclerView>>()
    val viewData: LiveData<List<ItemRecyclerView>> get() = _viewData

    fun getRatesByDate() {
        viewModelScope.launch(dispatchers.io) {
            val response = repository.getRatesByDate()
            if (response.isSuccessful) {
                _currency.postValue(response.body())
            } else {
                _errorMessage.postValue(response.errorBody().toString())
            }
        }
    }

    fun getLatestRates() {
        viewModelScope.launch(dispatchers.io) {
            val response = repository.getLatestRates()
            if (response.isSuccessful) {
                processResponse(response.body()!!)
            } else {
                _errorMessage.postValue(response.errorBody().toString())
            }
        }
    }

    private fun processResponse(currencyResponse: CurrencyResponse) {
        val viewData = mutableListOf<ItemRecyclerView>()
        val header = ItemHeaderDate(currencyResponse.date)
        viewData.add(header)
        val rates = currencyResponse.rates
        rates.entries.forEach {
            val itemRowCurrency = ItemRowCurrency(it.key, it.value)
            viewData.add(itemRowCurrency)
        }
        _viewData.postValue(viewData)
    }

    companion object {
        const val HEADER_DATE_ITEM = 0
        const val CURRENCY_RATE_ITEM = 1
    }
}