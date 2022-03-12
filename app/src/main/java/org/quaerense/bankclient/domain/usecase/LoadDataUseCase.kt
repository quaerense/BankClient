package org.quaerense.bankclient.domain.usecase

import org.quaerense.bankclient.domain.repository.CurrencyRepository
import org.quaerense.bankclient.domain.repository.UserRepository

class LoadDataUseCase(
    private val userRepository: UserRepository,
    private val currencyRepository: CurrencyRepository
) {

    operator fun invoke() {
        userRepository.loadData()
        currencyRepository.loadData()
    }
}