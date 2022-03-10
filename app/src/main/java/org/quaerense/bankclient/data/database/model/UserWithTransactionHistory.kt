package org.quaerense.bankclient.data.database.model

import androidx.room.Embedded
import androidx.room.Relation
import org.quaerense.bankclient.data.network.model.TransactionHistory

data class UserWithTransactionHistory(
    @Embedded val user: UserDbModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val transactionHistory: List<TransactionHistory>
)