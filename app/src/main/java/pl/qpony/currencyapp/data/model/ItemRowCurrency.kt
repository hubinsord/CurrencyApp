package pl.qpony.currencyapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import pl.qpony.currencyapp.R
import pl.qpony.currencyapp.domain.ItemRecyclerView
import pl.qpony.currencyapp.ui.currencylist.CurrencyListVM
import java.math.BigDecimal

@Parcelize
class ItemRowCurrency(
    val currency: String,
    val rate: BigDecimal,
    val date: String,
) : ItemRecyclerView, Parcelable {
    @IgnoredOnParcel
    override val layoutId: Int = R.layout.item_row_currency
    @IgnoredOnParcel
    override val viewType: Int = CurrencyListVM.ROW_CURRENCY_RATE_ITEM
}