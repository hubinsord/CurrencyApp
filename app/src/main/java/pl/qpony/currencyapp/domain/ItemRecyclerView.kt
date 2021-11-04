package pl.qpony.currencyapp.domain

import androidx.annotation.LayoutRes

interface ItemRecyclerView {
    @get:LayoutRes
    val layoutId: Int
    val viewType: Int get() = 0
}