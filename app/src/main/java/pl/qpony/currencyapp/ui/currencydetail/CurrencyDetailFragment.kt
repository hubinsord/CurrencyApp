package pl.qpony.currencyapp.ui.currencydetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import pl.qpony.currencyapp.R
import pl.qpony.currencyapp.databinding.FragmentCurrencyDetailBinding
import pl.qpony.currencyapp.ui.currencylist.BindableRecyclerViewAdapter

class CurrencyDetailFragment : Fragment(R.layout.fragment_currency_detail) {
    private var _binding: FragmentCurrencyDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CurrencyDetailVM by viewModels()
    private val args by navArgs<CurrencyDetailFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCurrencyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.loadData(args.itemRowCurrency)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}