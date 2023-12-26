package com.qanatdev.livecoinwatch.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.qanatdev.livecoinwatch.domain.CryptoEntity

object CryptoDiffCallback : DiffUtil.ItemCallback<CryptoEntity>() {

    override fun areItemsTheSame(oldItem: CryptoEntity, newItem: CryptoEntity): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CryptoEntity, newItem: CryptoEntity): Boolean {
        return oldItem == newItem
    }
}