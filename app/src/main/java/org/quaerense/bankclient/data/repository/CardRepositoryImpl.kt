package org.quaerense.bankclient.data.repository

import android.app.Application
import androidx.lifecycle.Transformations
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import org.quaerense.bankclient.data.database.AppDatabase
import org.quaerense.bankclient.data.mapper.CardMapper
import org.quaerense.bankclient.data.mapper.TransactionMapper
import org.quaerense.bankclient.data.worker.RefreshCardWorker
import org.quaerense.bankclient.domain.repository.CardRepository

class CardRepositoryImpl(private val application: Application) : CardRepository {

    private val dao = AppDatabase.getInstance(application).cardDao()
    private val cardMapper = CardMapper()
    private val transactionHistoryMapper = TransactionMapper()

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniquePeriodicWork(
            RefreshCardWorker.NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            RefreshCardWorker.makeRequest()
        )
    }

    override fun getCardList() = Transformations.map(dao.getAll()) { list ->
        list.map {
            val transactionHistory =
                transactionHistoryMapper.mapDbModelToEntity(it.transactionHistory)
            cardMapper.mapDbModelToEntity(it.card, transactionHistory)
        }
    }

    override fun getCard(cardNumber: String) = Transformations.map(dao.get(cardNumber)) {
        val transactionHistory = transactionHistoryMapper.mapDbModelToEntity(it.transactionHistory)
        cardMapper.mapDbModelToEntity(it.card, transactionHistory)
    }
}