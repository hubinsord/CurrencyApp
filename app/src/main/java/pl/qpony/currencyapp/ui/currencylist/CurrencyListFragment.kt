package pl.qpony.currencyapp.ui.currencylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import pl.qpony.currencyapp.R
import pl.qpony.currencyapp.data.model.ItemRowCurrency
import pl.qpony.currencyapp.databinding.FragmentCurrencyListBinding
import pl.qpony.currencyapp.domain.ItemRecyclerView

@AndroidEntryPoint
class CurrencyListFragment : Fragment(R.layout.fragment_currency_list),
    BindableRecyclerViewAdapter.Companion.BindableRecyclerViewAdapterListener {
    private var _binding: FragmentCurrencyListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CurrencyListVM by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        initViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClicked(itemRecyclerView: ItemRecyclerView) {
        when (itemRecyclerView) {
            is ItemRowCurrency -> {
                val action =
                    CurrencyListFragmentDirections.actionCurrencyListFragmentToCurrencyDetailFragment(itemRecyclerView)
                findNavController().navigate(action)
            }
        }
    }

    private fun initViews() {
        viewModel.getLatestRates()
        binding.listener = this
        binding.viewModel = viewModel
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.rvCurrenciesRates.layoutManager = linearLayoutManager
        binding.rvCurrenciesRates.addOnScrollListener(object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.loadMoreData()
            }
        })
    }
}