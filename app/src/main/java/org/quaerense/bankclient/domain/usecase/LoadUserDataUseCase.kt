package org.quaerense.bankclient.domain.usecase

import org.quaerense.bankclient.domain.repository.UserRepository

class LoadUserDataUseCase(private val repository: UserRepository) {

    operator fun invoke() {
        repository.loadData()
    }
}