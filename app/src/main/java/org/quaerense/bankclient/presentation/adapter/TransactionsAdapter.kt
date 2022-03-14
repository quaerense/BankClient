package org.quaerense.bankclient.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import org.quaerense.bankclient.R
import org.quaerense.bankclient.databinding.TransactionItemBinding
import org.quaerense.bankclient.domain.model.TransactionHistory

class TransactionsAdapter(private val context: Context) :
    ListAdapter<TransactionHistory, TransactionViewHolder>(TransactionDiffCallback) {

    var currencyChar: String = UNDEFINED

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = TransactionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = getItem(position)

        with(holder.binding) {
            Picasso.get().load(transaction.iconUrl)
                .error(R.drawable.ic_logo_transaction)
                .into(ivTransactionIcon)

            tvTransactionTitle.text = transaction.title
            tvTransactionDate.text = transaction.date
            val amount =
                "${context.getString(R.string.char_usd)} ${transaction.amount?.substring(1)}"
            tvTransactionAmount.text = amount
            val convertedAmount = transaction.convertedAmount
            tvConvertedTransactionAmount.text = convertedAmount
            val currencyCharWithMinus = "- $currencyChar"
            tvTransactionCurrencyChar.text = currencyCharWithMinus
        }
    }

    companion object {

        private const val UNDEFINED = ""
    }
}