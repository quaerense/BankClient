package org.quaerense.bankclient.domain.usecase

import androidx.lifecycle.LiveData
import org.quaerense.bankclient.domain.model.User
import org.quaerense.bankclient.domain.repository.UserRepository

class GetUserListUseCase(private val repository: UserRepository) {

    operator fun invoke(): LiveData<List<User>> {
        return repository.getUserList()
    }
}