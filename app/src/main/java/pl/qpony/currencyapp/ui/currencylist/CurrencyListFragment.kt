package pl.qpony.currencyapp.ui.currencylist

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pl.qpony.currencyapp.R
import pl.qpony.currencyapp.databinding.FragmentCurrencyListBinding

@AndroidEntryPoint
class CurrencyListFragment : Fragment(R.layout.fragment_currency_list) {

    private var _binding: FragmentCurrencyListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CurrencyListVM by viewModels()
    private lateinit var adapter: BindableRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        initViews()
        initObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initViews() {
        viewModel.getLatestRates()
        binding.viewModel = viewModel
        binding.rvCurrenciesRates.layoutManager = LinearLayoutManager(requireContext())
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        viewModel.currency.observe(viewLifecycleOwner, Observer {

        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Log.d("TAG", "error: $it")
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }
}