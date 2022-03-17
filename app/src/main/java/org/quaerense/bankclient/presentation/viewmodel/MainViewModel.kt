package org.quaerense.bankclient.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.quaerense.bankclient.data.repository.CardRepositoryImpl
import org.quaerense.bankclient.data.repository.CurrencyRepositoryImpl
import org.quaerense.bankclient.domain.usecase.GetCardUseCase
import org.quaerense.bankclient.domain.usecase.GetCurrencyRatioUseCase
import org.quaerense.bankclient.domain.usecase.LoadCardDataUseCase
import org.quaerense.bankclient.domain.usecase.LoadCurrencyDataUseCase

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val cardRepository = CardRepositoryImpl(application)
    private val currencyRepository = CurrencyRepositoryImpl(application)

    val getCardUseCase = GetCardUseCase(cardRepository)
    private val loadCardDataUseCase = LoadCardDataUseCase(cardRepository)
    private val loadCurrencyDataUseCase = LoadCurrencyDataUseCase(currencyRepository)
    private val getCurrencyRatioUseCase = GetCurrencyRatioUseCase(currencyRepository)

    private val _currencyRatio = MutableLiveData<Double>()
    val currencyRatio: LiveData<Double>
        get() = _currencyRatio

    fun setCurrency(targetCurrencyCharCode: String) {
        viewModelScope.launch {
            _currencyRatio.value = getCurrencyRatioUseCase(targetCurrencyCharCode)
        }
    }

    fun loadData() {
        loadCurrencyDataUseCase()
        loadCardDataUseCase()
    }

    init {
        loadData()
    }
}