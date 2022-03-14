package org.quaerense.bankclient.domain.repository

import org.quaerense.bankclient.domain.model.Currency

interface CurrencyRepository {

    fun loadData()

    fun getCurrency(charCode: String): Currency
}