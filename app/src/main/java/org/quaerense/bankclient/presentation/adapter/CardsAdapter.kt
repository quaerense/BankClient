package org.quaerense.bankclient.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.quaerense.bankclient.databinding.CardItemBinding
import org.quaerense.bankclient.domain.model.Card

class CardsAdapter(private val cardNumber: String) :
    ListAdapter<Card, CardViewHolder>(CardDiffCallback) {

    var onCardClickListener: ((item: Card) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = CardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = getItem(position)

        with(holder.binding) {
            ivCardLogo.setImageResource(card.iconId)
            tvCardNumber.text = card.cardNumber
            if (card.cardNumber == cardNumber) {
                ivMark.visibility = View.VISIBLE
            }
            root.setOnClickListener {
                onCardClickListener?.invoke(card)
            }
        }
    }

    companion object {

        private const val UNDEFINED = ""
    }
}