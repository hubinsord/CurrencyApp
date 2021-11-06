package pl.qpony.currencyapp.data.model

import pl.qpony.currencyapp.R
import pl.qpony.currencyapp.domain.ItemRecyclerView
import pl.qpony.currencyapp.ui.currencylist.CurrencyListVM
import java.math.BigDecimal

class ItemRowCurrency(val currency: String, val rate: BigDecimal) : ItemRecyclerView {
    override val layoutId: Int = R.layout.item_row_currency
    override val viewType: Int = CurrencyListVM.ROW_CURRENCY_RATE_ITEM
}