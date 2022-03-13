package org.quaerense.bankclient.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import org.quaerense.bankclient.domain.model.Card

object CardDiffCallback : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.cardNumber == newItem.cardNumber
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }
}