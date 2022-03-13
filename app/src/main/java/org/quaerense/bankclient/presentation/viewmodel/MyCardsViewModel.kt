package org.quaerense.bankclient.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.quaerense.bankclient.data.repository.UserRepositoryImpl
import org.quaerense.bankclient.domain.usecase.GetUserListUseCase
import org.quaerense.bankclient.domain.usecase.LoadUserDataUseCase

class MyCardsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepositoryImpl(application)

    val getUserListUseCase = GetUserListUseCase(repository)
    private val loadUserDataUseCase = LoadUserDataUseCase(repository)

    init {
        loadUserDataUseCase()
    }
}