package org.quaerense.bankclient.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_history")
data class TransactionHistoryDbModel(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val userId: String,
    val title: String?,
    val iconUrl: String?,
    val date: String?,
    val amount: String?
)