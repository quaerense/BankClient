package org.quaerense.bankclient.domain.repository

import org.quaerense.bankclient.domain.model.Currency

interface CurrencyRepository {

    fun loadData()

    suspend fun getCurrency(charCode: String): Currency
}