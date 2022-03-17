package org.quaerense.bankclient.data.worker

import android.content.Context
import androidx.work.*
import org.quaerense.bankclient.data.database.AppDatabase
import org.quaerense.bankclient.data.mapper.CurrencyMapper
import org.quaerense.bankclient.data.network.ApiFactory
import java.util.concurrent.TimeUnit

class RefreshCurrencyWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    private val apiService = ApiFactory.apiService
    private val dao = AppDatabase.getInstance(context).currencyDao()
    private val mapper = CurrencyMapper()

    override suspend fun doWork(): Result {
        try {
            val jsonContainerDto = apiService.getCurrencyRate()
            val currenciesDto = mapper.mapJsonContainerToList(jsonContainerDto)
            val currenciesDbModel = currenciesDto.map { mapper.mapDtoToDbModel(it) }
            dao.insertAll(currenciesDbModel)
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.failure()
        }

        return Result.success()
    }

    companion object {

        const val NAME = "RefreshCurrencyWorker"
        private const val REPEAT_INTERVAL = 15L

        fun makeRequest(): PeriodicWorkRequest {
            return PeriodicWorkRequest.Builder(
                RefreshCurrencyWorker::class.java,
                REPEAT_INTERVAL,
                TimeUnit.MINUTES
            ).build()
        }
    }
}