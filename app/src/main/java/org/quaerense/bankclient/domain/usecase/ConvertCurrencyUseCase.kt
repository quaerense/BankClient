package org.quaerense.bankclient.domain.usecase

import org.quaerense.bankclient.domain.repository.CurrencyRepository

class ConvertCurrencyUseCase(private val repository: CurrencyRepository) {

    suspend operator fun invoke(
        amount: Double,
        convertibleCurrencyCharCode: String,
        targetCurrencyCharCode: String
    ): Double {
        val convertibleCurrencyValue = repository.getCurrency(convertibleCurrencyCharCode).value ?: UNDEFINED_VALUE
        val targetCurrencyValue = repository.getCurrency(targetCurrencyCharCode).value ?: UNDEFINED_VALUE

        return amount * convertibleCurrencyValue * targetCurrencyValue
    }

    companion object {

        private const val UNDEFINED_VALUE = 0.0
    }
}