package org.quaerense.bankclient.data.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithTransactionHistory(
    @Embedded val user: UserDbModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val transactionHistory: List<TransactionHistoryDbModel>
)