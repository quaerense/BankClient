package org.quaerense.bankclient.data.mapper

import org.quaerense.bankclient.R
import org.quaerense.bankclient.data.database.model.UserDbModel
import org.quaerense.bankclient.data.network.model.UserDto
import org.quaerense.bankclient.domain.model.TransactionHistory
import org.quaerense.bankclient.domain.model.User

class UserMapper {

    fun mapDtoToDbModel(dto: UserDto) = with(dto) {
        UserDbModel(
            cardNumber = cardNumber,
            type = type,
            cardholderName = cardholderName,
            valid = valid,
            balance = balance
        )
    }

    fun mapDbModelToEntity(
        dbModel: UserDbModel,
        transactionHistory: List<TransactionHistory>
    ) = with(dbModel) {
        User(
            cardNumber = cardNumber,
            iconId = getIconId(type),
            cardholderName = cardholderName,
            valid = valid,
            balance = balance.toString(),
            transactionHistory = transactionHistory
        )
    }

    private fun getIconId(type: String?) = when (type) {
        MASTERCARD -> R.drawable.ic_logo_matercard
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