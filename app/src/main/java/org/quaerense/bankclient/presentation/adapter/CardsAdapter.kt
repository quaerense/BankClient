package org.quaerense.bankclient.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import org.quaerense.bankclient.R
import org.quaerense.bankclient.databinding.ItemCardSelectedBinding
import org.quaerense.bankclient.databinding.ItemCardUnselectedBinding
import org.quaerense.bankclient.domain.model.Card

class CardsAdapter(private val selectedCardNumber: String) :
    ListAdapter<Card, CardViewHolder>(CardDiffCallback) {

    var onCardClickListener: ((item: Card) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layout = if (viewType == VIEW_TYPE_UNSELECTED) {
            R.layout.item_card_unselected
        } else {
            R.layout.item_card_selected
        }

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )

        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = getItem(position)
        val binding = holder.binding

        binding.root.setOnClickListener {
            onCardClickListener?.invoke(card)
        }

        when (binding) {
            is ItemCardUnselectedBinding -> {
                binding.ivCardLogo.setImageResource(card.iconId)
                binding.tvCardNumber.text = card.cardNumber
            }
            is ItemCardSelectedBinding -> {
                binding.ivCardLogo.setImageResource(card.iconId)
                binding.tvCardNumber.text = card.cardNumber
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val card = getItem(position)

        if (card.cardNumber != selectedCardNumber) {
            return VIEW_TYPE_UNSELECTED
        }

        return VIEW_TYPE_SELECTED
    }

    companion object {

        private const val VIEW_TYPE_UNSELECTED = 0
        private const val VIEW_TYPE_SELECTED = 1
    }
}