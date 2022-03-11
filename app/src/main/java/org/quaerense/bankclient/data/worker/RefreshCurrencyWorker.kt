package org.quaerense.bankclient.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import org.quaerense.bankclient.data.database.AppDatabase
import org.quaerense.bankclient.data.mapper.CurrencyMapper
import org.quaerense.bankclient.data.network.ApiFactory

class RefreshCurrencyWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    private val apiService = ApiFactory.apiService
    private val dao = AppDatabase.getInstance(context).currencyDao()
    private val mapper = CurrencyMapper()

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val jsonContainerDto = apiService.getCurrencyRate()
                val currenciesDto = mapper.mapJsonContainerToList(jsonContainerDto)
                val currenciesDbModel = currenciesDto.map { mapper.mapDtoToDbModel(it) }
                dao.insertAll(currenciesDbModel)
            } catch (e: Exception) {

            }

            delay(DELAY_MILLIS)
        }
    }

    companion object {

        const val NAME = "RefreshCurrencyWorker"

        private const val DELAY_MILLIS = 300000L

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshCurrencyWorker>().build()
        }
    }
}