package org.quaerense.bankclient.data.mapper

import org.quaerense.bankclient.R
import org.quaerense.bankclient.data.database.model.CardDbModel
import org.quaerense.bankclient.data.network.model.CardDto
import org.quaerense.bankclient.domain.model.Transaction
import org.quaerense.bankclient.domain.model.Card

class CardMapper {

    fun mapDtoToDbModel(dto: CardDto) = with(dto) {
        cardNumber?.let {
            CardDbModel(
                cardNumber = it,
                type = type,
                cardholderName = cardholderName,
                valid = valid,
                balance = balance
            )
        }
    }

    fun mapDbModelToEntity(
        dbModel: CardDbModel,
        transactionHistory: List<Transaction>
    ) = with(dbModel) {
        Card(
            cardNumber = cardNumber,
            iconId = getIconId(type),
            cardholderName = cardholderName,
            valid = valid,
            balance = balance,
            transactionHistory = transactionHistory
        )
    }

    private fun getIconId(type: String?) = when (type) {
        MASTERCARD -> R.drawable.ic_logo_mastercard
        VISA -> R.drawable.ic_logo_visa
        UNION_PAY -> R.drawable.ic_logo_union_pay
        else -> UNDEFINED_LOGO
    }

    companion object {

        private const val MASTERCARD = "mastercard"
        private const val VISA = "visa"
        private const val UNION_PAY = "unionpay"

        private const val UNDEFINED_LOGO = -1
    }
}