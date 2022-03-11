package org.quaerense.bankclient.data.repository

import android.app.Application
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import org.quaerense.bankclient.data.database.AppDatabase
import org.quaerense.bankclient.data.mapper.TransactionHistoryMapper
import org.quaerense.bankclient.data.mapper.UserMapper
import org.quaerense.bankclient.data.worker.RefreshUserWorker
import org.quaerense.bankclient.domain.repository.UserRepository

class UserRepositoryImpl(private val application: Application) : UserRepository {

    private val dao = AppDatabase.getInstance(application).userDao()
    private val userMapper = UserMapper()
    private val transactionHistoryMapper = TransactionHistoryMapper()

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshUserWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshUserWorker.makeRequest()
        )
    }

    override fun getUserList() = Transformations.map(dao.getAll()) { list ->
        list.map {
            val transactionHistory =
                transactionHistoryMapper.mapDbModelToEntity(it.transactionHistory)
            userMapper.mapDbModelToEntity(it.user, transactionHistory)
        }
    }

    override fun getUser(cardNumber: String) = Transformations.map(dao.get(cardNumber)) {
        val transactionHistory = transactionHistoryMapper.mapDbModelToEntity(it.transactionHistory)
        userMapper.mapDbModelToEntity(it.user, transactionHistory)
    }
}