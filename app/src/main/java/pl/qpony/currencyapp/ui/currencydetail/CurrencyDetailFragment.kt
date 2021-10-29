package pl.qpony.currencyapp.ui.currencydetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.qpony.currencyapp.R
import pl.qpony.currencyapp.databinding.FragmentCurrencyDetailBinding


class CurrencyDetailFragment : Fragment(R.layout.fragment_currency_detail) {

    private var _binding: FragmentCurrencyDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCurrencyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}