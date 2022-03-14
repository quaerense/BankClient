package org.quaerense.bankclient.domain.usecase

import org.quaerense.bankclient.domain.repository.CurrencyRepository

class GetCurrencyRatioUseCase(private val repository: CurrencyRepository) {

    operator fun invoke(
        targetCurrencyCharCode: String
    ): Double {
        val convertibleCurrency = repository.getCurrency(CONVERTIBLE_CURRENCY)
        val convertibleCurrencyValue = convertibleCurrency.value ?: UNDEFINED_VALUE

        if (targetCurrencyCharCode == RUB) {
            return convertibleCurrencyValue
        }

        val targetCurrency = repository.getCurrency(targetCurrencyCharCode)
        val targetCurrencyValue = targetCurrency.value ?: UNDEFINED_VALUE

        return convertibleCurrencyValue / targetCurrencyValue
    }

    companion object {

        private const val UNDEFINED_VALUE = 0.0

        private const val CONVERTIBLE_CURRENCY = "USD"
        private const val RUB = "RUB"
        private const val ONE_TO_ONE_RATIO = 1.0
    }
}