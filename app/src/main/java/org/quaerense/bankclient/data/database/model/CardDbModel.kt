package org.quaerense.bankclient.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card")
data class CardDbModel(
    @PrimaryKey val cardNumber: String,
    val type: String?,
    val cardholderName: String?,
    val valid: String?,
    val balance: Double?
)