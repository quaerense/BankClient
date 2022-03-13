package org.quaerense.bankclient.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.quaerense.bankclient.databinding.CardItemBinding
import org.quaerense.bankclient.domain.model.User

class CardsAdapter : ListAdapter<User, CardViewHolder>(CardDiffCallback) {
    var onCardClickListener: ((item: User) -> Unit)? = null

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
            root.setOnClickListener {
                onCardClickListener?.invoke(card)
            }
        }
    }
}