package pl.qpony.currencyapp.ui.currencylist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import pl.qpony.currencyapp.R
import pl.qpony.currencyapp.databinding.FragmentCurrencyListBinding

@AndroidEntryPoint
class CurrencyListFragment : Fragment(R.layout.fragment_currency_list) {

    private var _binding: FragmentCurrencyListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CurrencyListVM by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.getRatesByDate()
        viewModel.getLatestRates()
        initObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initObservers() {
        viewModel.currency.observe(viewLifecycleOwner, Observer {
            Log.d("TAG", "success: ${it.success}, base: ${it.base}")
            Toast.makeText(requireContext(), "${it.success}, ${it.base}", Toast.LENGTH_LONG).show()
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Log.d("TAG", "error: $it")
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }
}