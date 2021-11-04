package pl.qpony.currencyapp.data.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.qpony.currencyapp.domain.ItemRecyclerView
import pl.qpony.currencyapp.ui.currencylist.BindableRecyclerViewAdapter

@BindingAdapter("itemRecyclerView")
fun bindItemViewModels(recyclerView: RecyclerView, itemsRecyclerView: List<ItemRecyclerView>?) {
    val adapter = getOrCreateAdapter(recyclerView)
    adapter.updateItems(itemsRecyclerView)
}

private fun getOrCreateAdapter(recyclerView: RecyclerView): BindableRecyclerViewAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is BindableRecyclerViewAdapter) {
        recyclerView.adapter as BindableRecyclerViewAdapter
    } else {
        val bindableRecyclerAdapter = BindableRecyclerViewAdapter()
        recyclerView.adapter = bindableRecyclerAdapter
        bindableRecyclerAdapter
    }
}
