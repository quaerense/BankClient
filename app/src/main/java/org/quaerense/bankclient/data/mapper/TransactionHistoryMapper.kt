package org.quaerense.bankclient.data.mapper

import org.quaerense.bankclient.data.database.model.TransactionHistoryDbModel
import org.quaerense.bankclient.data.network.model.TransactionHistoryDto
import org.quaerense.bankclient.domain.model.TransactionHistory

class TransactionHistoryMapper {

    fun mapDtoToDbModel(
        cardNumber: String,
        dto: List<TransactionHistoryDto>?
    ): List<TransactionHistoryDbModel> {
        val result = mutableListOf<TransactionHistoryDbModel>()
        if (dto == null) return result

        dto.map {
            result.add(
                TransactionHistoryDbModel(
                    cardNumber = cardNumber,
                    title = it.title,
                    iconUrl = it.iconUrl,
                    date = it.date,
                    amount = it.amount
                )
            )
        }

        return result
    }

    fun mapDbModelToEntity(dbModel: List<TransactionHistoryDbModel>): List<TransactionHistory> {
        val result = mutableListOf<TransactionHistory>()

        dbModel.map {
            result.add(
                TransactionHistory(
                    id = it.id,
                    title = it.title,
                    iconUrl = it.iconUrl,
                    date = it.date,
                    amount = it.amount
                )
            )
        }

        return result
    }
}