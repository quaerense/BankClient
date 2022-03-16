package org.quaerense.bankclient.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkerParameters
import org.quaerense.bankclient.data.database.AppDatabase
import org.quaerense.bankclient.data.mapper.CardMapper
import org.quaerense.bankclient.data.mapper.TransactionMapper
import org.quaerense.bankclient.data.network.ApiFactory
import java.util.concurrent.TimeUnit

class RefreshCardWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    private val apiService = ApiFactory.apiService
    private val dao = AppDatabase.getInstance(context).cardDao()
    private val cardMapper = CardMapper()
    private val transactionHistoryMapper = TransactionMapper()

    override suspend fun doWork(): Result {
        try {
            val cardContainerDto = apiService.getUsersAccountData()
            val cards = cardContainerDto.cards
            dao.deleteTransactionHistory()
            cards?.map {
                val cardDbModel = cardMapper.mapDtoToDbModel(it)
                if (cardDbModel != null) {
                    val transactionHistoryDbModel =
                        transactionHistoryMapper.mapDtoToDbModel(
                            cardDbModel.cardNumber,
                            it.transactionHistory
                        )
                    dao.insert(cardDbModel)
                    dao.insertTransactionHistory(transactionHistoryDbModel)
                }
            }
        } catch (e: Exception) {
            Result.failure()
        }

        return Result.success()
    }

    companion object {

        const val NAME = "RefreshCardWorker"
        private const val REPEAT_INTERVAL = 15L

        fun makeRequest(): PeriodicWorkRequest {
            return PeriodicWorkRequest.Builder(
                CoroutineWorker::class.java,
                REPEAT_INTERVAL,
                TimeUnit.MINUTES
            ).build()
        }
    }
}