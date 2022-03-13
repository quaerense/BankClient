package org.quaerense.bankclient.domain.repository

import androidx.lifecycle.LiveData
import org.quaerense.bankclient.data.database.model.CardWithTransactionHistory
import org.quaerense.bankclient.domain.model.Card

interface CardRepository {

    fun loadData()

    fun getCardList(): LiveData<List<Card>>

    fun getCard(cardNumber: String): LiveData<Card>
}