package org.quaerense.bankclient.data.database.model

import androidx.room.Entity

@Entity(tableName = "user")
class UserDbModel(
    var cardNumber: String?,
    var type: String?,
    var cardholderName: String?,
    var valid: String?,
    var balance: Double?,
)