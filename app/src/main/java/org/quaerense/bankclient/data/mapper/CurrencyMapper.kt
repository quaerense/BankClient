package org.quaerense.bankclient.data.mapper

import com.google.gson.Gson
import org.quaerense.bankclient.data.database.model.CurrencyDbModel
import org.quaerense.bankclient.data.network.model.CurrencyDto
import org.quaerense.bankclient.data.network.model.CurrencyJsonContainerDto
import org.quaerense.bankclient.domain.model.Currency

class CurrencyMapper {

    fun mapJsonContainerToList(jsonContainer: CurrencyJsonContainerDto): List<CurrencyDto> {
        val result = mutableListOf<CurrencyDto>()

        val jsonObject = jsonContainer.jsonObject ?: return result
        val currencyKeySet = jsonObject.keySet()
        for (currencyKey in currencyKeySet) {
            val currency = Gson().fromJson(
                jsonObject.getAsJsonObject(currencyKey),
                CurrencyDto::class.java
            )
            result.add(currency)
        }

        return result
    }

    fun mapDtoToDbModel(dto: CurrencyDto) = with(dto) {
        CurrencyDbModel(id, charCode, name, value)
    }

    fun mapDbModelToEntity(dbModel: CurrencyDbModel) = with(dbModel) {
        Currency(charCode, name, value.toString())
    }
}