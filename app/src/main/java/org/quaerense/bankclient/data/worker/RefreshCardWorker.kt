package org.quaerense.bankclient.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import org.quaerense.bankclient.data.database.AppDatabase
import org.quaerense.bankclient.data.mapper.TransactionHistoryMapper
import org.quaerense.bankclient.data.mapper.CardMapper
import org.quaerense.bankclient.data.network.ApiFactory

class RefreshCardWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    private val apiService = ApiFactory.apiService
    private val dao = AppDatabase.getInstance(context).cardDao()
    private val cardMapper = CardMapper()
    private val transactionHistoryMapper = TransactionHistoryMapper()

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val cardContainerDto = apiService.getUsersAccountData()
                val cards = cardContainerDto.cards
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

            }

            delay(DELAY_MILLIS)
        }
    }

    companion object {

        const val NAME = "RefreshCardWorker"

        private const val DELAY_MILLIS = 300000L

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshCardWorker>().build()
        }
    }
}