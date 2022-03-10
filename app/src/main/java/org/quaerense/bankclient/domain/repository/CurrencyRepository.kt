package org.quaerense.bankclient.domain.repository

import androidx.lifecycle.LiveData
import java.util.*

interface CurrencyRepository {

    fun loadData()

    fun getCurrency(charCode: String): LiveData<Currency>
}