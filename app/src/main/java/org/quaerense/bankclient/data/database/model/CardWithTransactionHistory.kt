package org.quaerense.bankclient.data.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class CardWithTransactionHistory(
    @Embedded val card: CardDbModel,
    @Relation(
        parentColumn = "cardNumber",
        entityColumn = "cardNumber"
    )
    val transactionHistory: List<TransactionHistoryDbModel>
)