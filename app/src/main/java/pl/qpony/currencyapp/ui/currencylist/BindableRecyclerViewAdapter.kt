package pl.qpony.currencyapp.ui.currencylist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import pl.qpony.currencyapp.domain.ItemRecyclerView


class BindableRecyclerViewAdapter : RecyclerView.Adapter<BindableRecyclerViewAdapter.BindableViewHolder>() {
    private var itemsRecyclerView: List<ItemRecyclerView> = emptyList()
    private val viewTypeToLayoutId: MutableMap<Int, Int> = mutableMapOf()
    private lateinit var listener: BindableRecyclerViewAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layoutId = viewTypeToLayoutId[viewType] ?: 0
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, layoutId, parent, false)
        return BindableViewHolder(binding, listener)
    }

    override fun getItemViewType(position: Int): Int {
        val item = itemsRecyclerView[position]
        if (!viewTypeToLayoutId.containsKey(item.viewType)) {
            viewTypeToLayoutId[item.viewType] = item.layoutId
        }
        return item.viewType
    }

    override fun onBindViewHolder(holder: BindableViewHolder, position: Int) {
        holder.bind(itemsRecyclerView[position])
    }

    override fun getItemCount(): Int = itemsRecyclerView.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<ItemRecyclerView>?) {
        itemsRecyclerView = items ?: emptyList()
        notifyDataSetChanged()
    }

    fun initListener(listener: BindableRecyclerViewAdapterListener) {
        this.listener = listener
    }

    class BindableViewHolder(
        private val binding: ViewDataBinding,
        private val listener: BindableRecyclerViewAdapterListener
        ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itemRecyclerView: ItemRecyclerView) {
            binding.setVariable(BR.itemRecyclerView, itemRecyclerView)
            binding.root.setOnClickListener { listener.onItemClicked(itemRecyclerView) }
        }
    }

    companion object {
        interface BindableRecyclerViewAdapterListener {
            fun onItemClicked(itemRecyclerView: ItemRecyclerView)
        }
    }
}