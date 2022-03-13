package org.quaerense.bankclient.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.quaerense.bankclient.data.repository.CardRepositoryImpl
import org.quaerense.bankclient.domain.usecase.GetCardListUseCase
import org.quaerense.bankclient.domain.usecase.LoadCardDataUseCase

class MyCardsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CardRepositoryImpl(application)

    val getCardListUseCase = GetCardListUseCase(repository)
    private val loadCardDataUseCase = LoadCardDataUseCase(repository)

    init {
        loadCardDataUseCase()
    }
}