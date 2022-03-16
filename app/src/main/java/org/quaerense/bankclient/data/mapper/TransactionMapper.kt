package org.quaerense.bankclient.data.mapper

import org.quaerense.bankclient.data.database.model.TransactionDbModel
import org.quaerense.bankclient.data.network.model.TransactionDto
import org.quaerense.bankclient.domain.model.Transaction

class TransactionMapper {

    fun mapDtoToDbModel(
        cardNumber: String,
        dto: List<TransactionDto>?
    ): List<TransactionDbModel> {
        val result = mutableListOf<TransactionDbModel>()
        if (dto == null) return result

        dto.map {
            result.add(
                TransactionDbModel(
                    cardNumber = cardNumber,
                    title = it.title,
                    iconUrl = it.iconUrl,
                    date = it.date,
                    amount = it.amount?.substring(1)?.toDouble() ?: 0.0
                )
            )
        }

        return result
    }

    fun mapDbModelToEntity(dbModel: List<TransactionDbModel>): List<Transaction> {
        val result = mutableListOf<Transaction>()

        dbModel.map {
            result.add(
                Transaction(
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