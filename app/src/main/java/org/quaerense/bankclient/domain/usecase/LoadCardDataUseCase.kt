package org.quaerense.bankclient.domain.usecase

import org.quaerense.bankclient.domain.repository.CardRepository

class LoadCardDataUseCase(private val repository: CardRepository) {

    operator fun invoke() {
        repository.loadData()
    }
}