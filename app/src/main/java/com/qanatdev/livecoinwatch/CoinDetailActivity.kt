package com.qanatdev.livecoinwatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        val tvPrice: TextView = findViewById(R.id.tvPrice)
        val tvMinPrice: TextView = findViewById(R.id.tvMinPrice)
        val tvMaxPrice: TextView = findViewById(R.id.tvMaxPrice)
        val tvLastMarket: TextView = findViewById(R.id.tvLastMarket)
        val tvLastUpdate: TextView = findViewById(R.id.tvLastUpdate)
        val tvFromSymbol: TextView = findViewById(R.id.tvFromSymbol)
        val tvToSymbol: TextView = findViewById(R.id.tvToSymbol)
        val ivLogoCoin: ImageView = findViewById(R.id.ivLogoCoin)


        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProviders.of(this)[CoinViewModel::class.java]
        if (fromSymbol != null) {
            viewModel.getDetailInfo(fromSymbol).observe(this, Observer {
                tvPrice.text = it.price
                tvMinPrice.text = it.lowDay
                tvMaxPrice.text = it.highDay
                tvLastMarket.text = it.lastMarket
                tvLastUpdate.text = it.getFormattedTime()
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.toSymbol
                Picasso.get().load(it.getFullImageUrl()).into(ivLogoCoin)
            })
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}
