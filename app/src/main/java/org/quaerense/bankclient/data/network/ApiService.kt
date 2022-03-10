package org.quaerense.bankclient.data.network

import org.quaerense.bankclient.data.network.model.CurrencyContainerDto
import org.quaerense.bankclient.data.network.model.UserContainerDto
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getCurrencyRate(
        @Url url: String = URL_CURRENCY_RATE
    ): CurrencyContainerDto

    @GET
    suspend fun getUsersAccountData(
        @Url url: String = URL_USERS_ACCOUNT_DATA
    ): UserContainerDto

    companion object {

        private const val URL_CURRENCY_RATE = "https://www.cbr-xml-daily.ru/daily_json.js"
        private const val URL_USERS_ACCOUNT_DATA =
            "https://hr.peterpartner.net/test/android/v1/users.json"
    }
}