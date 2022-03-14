package org.quaerense.bankclient.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import org.quaerense.bankclient.domain.model.TransactionHistory

object TransactionDiffCallback : DiffUtil.ItemCallback<TransactionHistory>() {
    override fun areItemsTheSame(
        oldItem: TransactionHistory,
        newItem: TransactionHistory
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: TransactionHistory,
        newItem: TransactionHistory
    ): Boolean {
        return oldItem == newItem
    }
}