package org.quaerense.bankclient.data.database.model

import androidx.room.Entity

@Entity(tableName = "transaction_history")
data class TransactionHistoryDbModel(
    val userId: String,
    val title: String?,
    val iconUrl: String?,
    val date: String?,
    val amount: String?
)