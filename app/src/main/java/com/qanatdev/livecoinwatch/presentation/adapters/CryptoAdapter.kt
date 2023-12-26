package com.qanatdev.livecoinwatch.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.qanatdev.livecoinwatch.utils.convertTimestampToTime
import com.qanatdev.livecoinwatch.utils.cutNumbers
import com.qanatdev.livecoinwatch.R
import com.qanatdev.livecoinwatch.data.api.ApiFactory.BASE_IMAGE_URL
import com.qanatdev.livecoinwatch.databinding.ItemCoinInfoBinding
import com.qanatdev.livecoinwatch.domain.CryptoEntity
import com.squareup.picasso.Picasso


class CryptoAdapter(
    private val context: Context
) : ListAdapter<CryptoEntity, CryptoViewHolder>(CryptoDiffCallback) {

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CryptoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val coin = getItem(position)
        with(holder.binding) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                tvPrice.text = cutNumbers(price)
                tvLastUpdate.text = convertTimestampToTime(lastUpdate)
                Picasso.get().load(BASE_IMAGE_URL+ imageUrl).into(ivLogoCoin)
                root.setOnClickListener {
                    onCoinClickListener?.onCoinClick(this)
                }
            }
        }
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CryptoEntity)
    }
}