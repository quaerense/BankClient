package org.quaerense.bankclient.data.database.model

import androidx.room.Entity

@Entity(tableName = "transaction_history")
data class TransactionHistoryDbModel(
    var userId: String,
    var title: String?,
    var iconUrl: String?,
    var date: String?,
    var amount: String?
)