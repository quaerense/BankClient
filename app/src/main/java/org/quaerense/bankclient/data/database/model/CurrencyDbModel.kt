package org.quaerense.bankclient.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyDbModel(
    @PrimaryKey val id: String,
    val numCode: String?,
    val charCode: String?,
    val nominal: Int?,
    val name: String?,
    val value: Double?,
    val previous: Double?
)