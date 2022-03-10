package org.quaerense.bankclient.data.database.model

import androidx.room.Entity
import org.quaerense.bankclient.data.network.model.TransactionHistory

@Entity(tableName = "user")
class UserDbModel(
    var cardNumber: String?,
    var type: String?,
    var cardholderName: String?,
    var valid: String?,
    var balance: Double?,
    var transactionHistory: List<TransactionHistory>?
)