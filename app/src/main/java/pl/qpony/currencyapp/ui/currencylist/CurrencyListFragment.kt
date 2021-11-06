package pl.qpony.currencyapp.ui.currencylist

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log.i
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import pl.qpony.currencyapp.R
import pl.qpony.currencyapp.databinding.FragmentCurrencyListBinding

@AndroidEntryPoint
class CurrencyListFragment : Fragment(R.layout.fragment_currency_list) {

    private var _binding: FragmentCurrencyListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CurrencyListVM by viewModels()
    private lateinit var  scrollListener: EndlessRecyclerViewScrollListener

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

    private fun initViews() {
        viewModel.getLatestRates()
        binding.viewModel = viewModel
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.rvCurrenciesRates.layoutManager = linearLayoutManager
        binding.rvCurrenciesRates.addOnScrollListener(object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.getRatesByDate()
            }
        })
    }

//    private fun loadMoreData() {
//        viewModel.getRatesByDate()
//    }
}