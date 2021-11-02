package pl.qpony.currencyapp.ui.currencylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.qpony.currencyapp.data.model.Currency
import pl.qpony.currencyapp.domain.CurrencyRepository
import pl.qpony.currencyapp.util.DispatcherProvider
import javax.inject.Inject

@HiltViewModel
class CurrencyListVM @Inject constructor(
    private val repository: CurrencyRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _currency = MutableLiveData<Currency>()
    val currency: LiveData<Currency> get() = _currency

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

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

    fun getLatestRates(){
        viewModelScope.launch(dispatchers.io){
            val response = repository.getLatestRates()
            if (response.isSuccessful) {
                _currency.postValue(response.body())
            } else {
                _errorMessage.postValue(response.errorBody().toString())
            }
        }
    }
}