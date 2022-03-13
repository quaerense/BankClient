package org.quaerense.bankclient.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.quaerense.bankclient.data.repository.CardRepositoryImpl
import org.quaerense.bankclient.data.repository.CurrencyRepositoryImpl
import org.quaerense.bankclient.domain.usecase.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val cardRepository = CardRepositoryImpl(application)
    private val currencyRepository = CurrencyRepositoryImpl(application)

    val getCardListUseCase = GetCardListUseCase(cardRepository)
    val getCardUseCase = GetCardUseCase(cardRepository)
    val loadCardDataUseCase = LoadCardDataUseCase(cardRepository)
    val convertCurrencyUseCase = ConvertCurrencyUseCase(currencyRepository)

    private val _convertedBalance = MutableLiveData<String>()
    val convertedBalance: LiveData<String>
        get() = _convertedBalance

    private fun convertBalance(balance: Double) {

    }

    init {
        loadCardDataUseCase()
    }
}