package org.quaerense.bankclient.data.database.model

import androidx.room.Entity

@Entity(tableName = "currency")
data class CurrencyDbModel(
    val id: String,
    val numCode: String?,
    val charCode: String?,
    val nominal: Int?,
    val name: String?,
    val value: Double?,
    val previous: Double?
)