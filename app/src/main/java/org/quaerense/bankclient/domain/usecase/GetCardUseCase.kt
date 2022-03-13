package org.quaerense.bankclient.domain.usecase

import androidx.lifecycle.LiveData
import org.quaerense.bankclient.domain.model.Card
import org.quaerense.bankclient.domain.repository.CardRepository

class GetCardUseCase(private val repository: CardRepository) {

    operator fun invoke(cardNumber: String): LiveData<Card> {
        return repository.getCard(cardNumber)
    }
}