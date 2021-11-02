package pl.qpony.currencyapp.ui.currencylist

import android.annotation.SuppressLint
import android.util.Log.i
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.qpony.currencyapp.databinding.ItemRowCurrencyBinding
import java.math.BigDecimal

class CurrencyListAdapter(var currencyRates: List<Pair<String, BigDecimal>>) : RecyclerView.Adapter<CurrencyListAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemRowCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowCurrencyBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        i("TEST", "TEST $currencyRates")

        with(holder.binding){
            tvCurrency.text = currencyRates[position].first
            tvRate.text = currencyRates[position].second.toString()
        }
    }

    override fun getItemCount(): Int  = currencyRates.size
}