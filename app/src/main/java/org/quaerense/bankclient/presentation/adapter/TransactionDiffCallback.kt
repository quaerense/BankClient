package org.quaerense.bankclient.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import org.quaerense.bankclient.domain.model.Transaction

object TransactionDiffCallback : DiffUtil.ItemCallback<Transaction>() {
    override fun areItemsTheSame(
        oldItem: Transaction,
        newItem: Transaction
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Transaction,
        newItem: Transaction
    ): Boolean {
        return oldItem == newItem
    }
}