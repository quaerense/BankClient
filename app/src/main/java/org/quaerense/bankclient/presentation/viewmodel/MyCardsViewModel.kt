package org.quaerense.bankclient.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.quaerense.bankclient.data.repository.CardRepositoryImpl
import org.quaerense.bankclient.data.repository.CurrencyRepositoryImpl
import org.quaerense.bankclient.domain.usecase.GetCardListUseCase
import org.quaerense.bankclient.domain.usecase.LoadCardDataUseCase
import org.quaerense.bankclient.domain.usecase.LoadCurrencyDataUseCase

class MyCardsViewModel(application: Application) : AndroidViewModel(application) {

    private val cardRepository = CardRepositoryImpl(application)
    private val currencyRepository = CurrencyRepositoryImpl(application)

    val getCardListUseCase = GetCardListUseCase(cardRepository)
    private val loadCardDataUseCase = LoadCardDataUseCase(cardRepository)
    private val loadCurrencyDataUseCase = LoadCurrencyDataUseCase(currencyRepository)

    fun loadData() {
        loadCardDataUseCase()
        loadCurrencyDataUseCase()
    }

    init {
        loadData()
    }
}