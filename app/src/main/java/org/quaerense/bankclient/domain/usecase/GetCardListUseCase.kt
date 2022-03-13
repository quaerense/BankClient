package org.quaerense.bankclient.domain.usecase

import androidx.lifecycle.LiveData
import org.quaerense.bankclient.domain.model.Card
import org.quaerense.bankclient.domain.repository.CardRepository

class GetCardListUseCase(private val repository: CardRepository) {

    operator fun invoke(): LiveData<List<Card>> {
        return repository.getCardList()
    }
}