package com.qanatdev.livecoinwatch.presentation

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.qanatdev.livecoinwatch.R
import com.qanatdev.livecoinwatch.databinding.ActivityCoinPrceListBinding
import com.qanatdev.livecoinwatch.domain.CryptoEntity
import com.qanatdev.livecoinwatch.presentation.adapters.CoinInfoAdapter
import javax.inject.Inject


class CoinPriceListActivity : AppCompatActivity() {


    private lateinit var viewModel: CoinViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val component by lazy {
        (application as CryptoApp).component
    }

    private val binding by lazy {
        ActivityCoinPrceListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        launchWelcomeActivity()

        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CryptoEntity) {
                if (isOnePaneMode()) {
                    launchDetailActivity(coinPriceInfo.fromSymbol)
                } else {
                    launchDetailFragment(coinPriceInfo.fromSymbol)
                }
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
        viewModel = ViewModelProvider(this, mainViewModelFactory)[CoinViewModel::class.java]
        viewModel.cryptoList.observe(this) {
            adapter.submitList(it)
        }

    }

    private fun isOnePaneMode() = binding.fragmentContainer == null

    private fun launchDetailActivity(fromSymbol: String) {
        val intent = CoinDetailActivity.newIntent(
            this@CoinPriceListActivity,
            fromSymbol
        )
        startActivity(intent)
    }

    private fun launchDetailFragment(fromSymbol: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }

    private fun launchWelcomeActivity(){
        val prefs: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val firstRun: Boolean = prefs.getBoolean(KEY_FIRST_RUN, true)

        if (firstRun) {
            startActivity(Intent(this, WelcomeActivity::class.java))
        }
    }

    companion object{
        private val PREFS_NAME = "MyPrefsFile"
        private val KEY_FIRST_RUN = "firstRun"
    }
}
