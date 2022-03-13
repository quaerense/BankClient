package org.quaerense.bankclient.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import org.quaerense.bankclient.domain.model.User

object CardDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.cardNumber == newItem.cardNumber
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}