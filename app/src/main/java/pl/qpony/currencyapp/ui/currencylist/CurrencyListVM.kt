package pl.qpony.currencyapp.ui.currencylist

import android.os.Build
import androidx.annotation.RequiresApi
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
import java.time.LocalDate
import java.time.Period
import javax.inject.Inject

@HiltViewModel
class CurrencyListVM @Inject constructor(
    private val repository: CurrencyRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private val _currency = MutableLiveData<CurrencyResponse>()
    private val currency: LiveData<CurrencyResponse> get() = _currency

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _viewData = MutableLiveData<List<ItemRecyclerView>>()
    val viewData: LiveData<List<ItemRecyclerView>> get() = _viewData

    fun getLatestRates() {
        viewModelScope.launch(dispatchers.io) {
            if (currency.value == null) {
                val response = repository.getLatestRates()
                if (response.isSuccessful) {
                    _currency.postValue(response.body())
                    response.body()?.let { updateViewData(it) }
                } else {
                    _errorMessage.postValue(response.errorBody().toString())
                }
            }
        }
    }

    fun getRatesByDate(date: String) {
        viewModelScope.launch(dispatchers.io) {
            val response = repository.getRatesByDate(date)
            if (response.isSuccessful) {
                _currency.postValue(response.body())
                response.body()?.let { updateViewData(it) }
            } else {
                _errorMessage.postValue(response.errorBody().toString())
            }
        }
    }

    private fun updateViewData(currencyResponse: CurrencyResponse) {
        val viewDataToBeAdded = when (viewData.value) {
            null -> {
                createViewData(mutableListOf(), currencyResponse)
            }
            else -> {
                createViewData(viewData.value!!.toMutableList(), currencyResponse)
            }
        }
        _viewData.postValue(viewDataToBeAdded)
    }

    private fun createViewData(
        list: MutableList<ItemRecyclerView>,
        currencyResponse: CurrencyResponse,
    ): MutableList<ItemRecyclerView> {
        val itemHeaderDate = ItemHeaderDate(currencyResponse.date)
        list.add(itemHeaderDate)
        currencyResponse.rates.entries.forEach { it ->
            val itemRowCurrency = ItemRowCurrency(it.key, it.value, currencyResponse.date)
            list.add(itemRowCurrency)
        }
        return list
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDecrementedDate(): String {
        val period = Period.of(0, 0, 1)
        val date = currency.value?.date
        val dateMinusOneDay = LocalDate.parse(date).minus(period)
        return dateMinusOneDay.toString()
    }

    companion object {
        const val HEADER_DATE_ITEM = 0
        const val ROW_CURRENCY_RATE_ITEM = 1
    }
}
