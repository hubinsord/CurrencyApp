package pl.qpony.currencyapp.ui.currencydetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.qpony.currencyapp.data.model.ItemRowCurrency

class CurrencyDetailVM : ViewModel() {
    private val _viewData = MutableLiveData<ItemRowCurrency>()
    val viewData: LiveData<ItemRowCurrency> get() = _viewData

    fun loadData(itemRowCurrency: ItemRowCurrency){
        _viewData.postValue(itemRowCurrency)
    }
}