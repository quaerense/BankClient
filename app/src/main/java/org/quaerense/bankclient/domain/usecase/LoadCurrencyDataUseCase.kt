package org.quaerense.bankclient.domain.usecase

import org.quaerense.bankclient.domain.repository.CurrencyRepository

class LoadCurrencyDataUseCase(private val repository: CurrencyRepository) {

    operator fun invoke() {
        repository.loadData()
    }
}