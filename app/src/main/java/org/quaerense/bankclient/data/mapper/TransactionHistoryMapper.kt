package org.quaerense.bankclient.data.mapper

import org.quaerense.bankclient.data.database.model.TransactionHistoryDbModel
import org.quaerense.bankclient.data.network.model.TransactionHistoryDto
import org.quaerense.bankclient.domain.model.TransactionHistory

class TransactionHistoryMapper {

    fun mapDtoToDbModel(userId: Long, dto: TransactionHistoryDto) = with(dto) {
        TransactionHistoryDbModel(
            userId = userId,
            title = title,
            iconUrl = iconUrl,
            date = date,
            amount = amount
        )
    }

    fun mapDbModelToEntity(dbModel: TransactionHistoryDbModel) = with(dbModel) {
        TransactionHistory(
            title = title,
            iconUrl = iconUrl,
            date = date,
            amount = amount
        )
    }
}