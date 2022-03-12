package org.quaerense.bankclient.domain.usecase

import androidx.lifecycle.LiveData
import org.quaerense.bankclient.domain.model.User
import org.quaerense.bankclient.domain.repository.UserRepository

class GetUserUseCase(private val repository: UserRepository) {

    operator fun invoke(cardNumber: String): LiveData<User> {
        return repository.getUser(cardNumber)
    }
}