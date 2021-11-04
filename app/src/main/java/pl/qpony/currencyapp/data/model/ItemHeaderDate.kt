package pl.qpony.currencyapp.data.model

import pl.qpony.currencyapp.R
import pl.qpony.currencyapp.domain.ItemRecyclerView
import pl.qpony.currencyapp.ui.currencylist.CurrencyListVM

class ItemHeaderDate(val date: String): ItemRecyclerView {
    override val layoutId: Int = R.layout.item_header_date
    override val viewType: Int = CurrencyListVM.HEADER_DATE_ITEM
}