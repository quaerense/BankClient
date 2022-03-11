package org.quaerense.bankclient.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDbModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val cardNumber: String?,
    val type: String?,
    val cardholderName: String?,
    val valid: String?,
    val balance: Double?
)