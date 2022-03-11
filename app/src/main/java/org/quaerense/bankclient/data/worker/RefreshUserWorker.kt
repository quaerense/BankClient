package org.quaerense.bankclient.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import org.quaerense.bankclient.data.database.AppDatabase
import org.quaerense.bankclient.data.mapper.TransactionHistoryMapper
import org.quaerense.bankclient.data.mapper.UserMapper
import org.quaerense.bankclient.data.network.ApiFactory

class RefreshUserWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    private val apiService = ApiFactory.apiService
    private val dao = AppDatabase.getInstance(context).userDao()
    private val userMapper = UserMapper()
    private val transactionHistoryMapper = TransactionHistoryMapper()

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val userContainerDto = apiService.getUsersAccountData()
                val users = userContainerDto.users
                users?.map {
                    val userDbModel = userMapper.mapDtoToDbModel(it)
                    if (userDbModel != null) {
                        val transactionHistoryDbModel =
                            transactionHistoryMapper.mapDtoToDbModel(
                                userDbModel.cardNumber,
                                it.transactionHistory
                            )
                        dao.insert(userDbModel)
                        dao.insertTransactionHistory(transactionHistoryDbModel)
                    }
                }
            } catch (e: Exception) {

            }

            delay(DELAY_MILLIS)
        }
    }

    companion object {

        const val NAME = "RefreshUserWorker"

        private const val DELAY_MILLIS = 300000L

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshUserWorker>().build()
        }
    }
}