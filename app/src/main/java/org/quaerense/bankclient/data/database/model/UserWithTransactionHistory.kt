package org.quaerense.bankclient.data.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithTransactionHistory(
    @Embedded val user: UserDbModel,
    @Relation(
        parentColumn = "cardNumber",
        entityColumn = "userCardNumber"
    )
    val transactionHistory: List<TransactionHistoryDbModel>
)