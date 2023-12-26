package com.qanatdev.livecoinwatch.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.qanatdev.livecoinwatch.data.api.ApiFactory.BASE_IMAGE_URL
import com.qanatdev.livecoinwatch.databinding.FragmentCryptoDetailBinding
import com.qanatdev.livecoinwatch.utils.convertTimestampToTime
import com.qanatdev.livecoinwatch.utils.cutNumbers
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CryptoDetailFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val component by lazy {
        (requireActivity().application as LiveCoinWatch).component
    }


    private var _binding: FragmentCryptoDetailBinding? = null
    private val binding: FragmentCryptoDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentCoinDetailBinding is null")


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCryptoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fromSymbol = getSymbol()
        viewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol).observe(viewLifecycleOwner) {
            with(binding) {
                tvPrice.text = cutNumbers(it.price)
                tvMinPrice.text = cutNumbers(it.lowDay)
                tvMaxPrice.text = cutNumbers(it.highDay)
                tvLastMarket.text = it.lastMarket
                tvLastUpdate.text = convertTimestampToTime(it.lastUpdate)
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.toSymbol
                Picasso.get().load(BASE_IMAGE_URL + it.imageUrl).into(ivLogoCoin)
            }
        }

        backToMainActivity()
    }

    private fun backToMainActivity(){
        binding.buttonToBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun getSymbol(): String {
        return requireArguments().getString(EXTRA_FROM_SYMBOL, EMPTY_SYMBOL)
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL = ""

        fun newInstance(fromSymbol: String): Fragment {
            return CryptoDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_FROM_SYMBOL, fromSymbol)
                }
            }
        }
    }
}