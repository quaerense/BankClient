package org.quaerense.bankclient.data.database.model

import androidx.room.Entity

@Entity(tableName = "currency")
data class CurrencyDbModel(
    var id: String,
    var numCode: String?,
    var charCode: String?,
    var nominal: Int?,
    var name: String?,
    var value: Double?,
    var previous: Double?
)