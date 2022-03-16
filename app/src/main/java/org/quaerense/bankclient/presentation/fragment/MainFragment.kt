package org.quaerense.bankclient.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.quaerense.bankclient.R
import org.quaerense.bankclient.databinding.FragmentMainBinding
import org.quaerense.bankclient.presentation.MainActivity.Companion.APP_PREFERENCES
import org.quaerense.bankclient.presentation.MainActivity.Companion.CARD_NUMBER
import org.quaerense.bankclient.presentation.MainActivity.Companion.UNDEFINED_CARD_NUMBER
import org.quaerense.bankclient.presentation.adapter.TransactionsAdapter
import org.quaerense.bankclient.presentation.viewmodel.MainViewModel

class MainFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    private var cardNumber: String = UNDEFINED_CARD_NUMBER

    private var currencyChar: String = UNDEFINED_CURRENCY_CHAR

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val adapter by lazy {
        TransactionsAdapter()
    }

    private val preferences by lazy {
        requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseCardNumberPreference()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTransactionHistory.adapter = adapter
        binding.srlLoadCardInfo.setOnRefreshListener(this)
        parseCurrencyPreference()
        setOnClickListeners()
        setObservers()
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onRefresh() {
        binding.srlLoadCardInfo.isRefreshing = true
        loadData()
        binding.srlLoadCardInfo.isRefreshing = false
    }

    private fun loadData() {
        viewModel.loadData()
    }

    private fun setOnClickListeners() {
        with(binding) {
            cvCard.setOnClickListener {
                launchMyCardsFragment()
            }
            cvButtonGBP.setOnClickListener {
                it as CardView
                selectCurrency(it, GBP)
                adapter.currencyChar = getString(R.string.char_gbp)
                deselectCurrency(binding.cvButtonEUR)
                deselectCurrency(binding.cvButtonRUB)
            }
            cvButtonEUR.setOnClickListener {
                it as CardView
                selectCurrency(it, EUR)
                adapter.currencyChar = getString(R.string.char_eur)
                deselectCurrency(binding.cvButtonGBP)
                deselectCurrency(binding.cvButtonRUB)
            }
            cvButtonRUB.setOnClickListener {
                it as CardView
                selectCurrency(it, RUB)
                binding.tvConvertedBalanceChar.text = getString(R.string.char_rub)
                adapter.currencyChar = getString(R.string.char_rub)
                deselectCurrency(binding.cvButtonGBP)
                deselectCurrency(binding.cvButtonEUR)
            }
        }
    }

    private fun selectCurrency(cardView: CardView, currencyCharCode: String) {
        cardView.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
        val constraintLayout = cardView.getChildAt(0) as ViewGroup
        for (i in 0 until constraintLayout.childCount) {
            val view = constraintLayout.getChildAt(i)
            if (view is TextView) {
                view.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
        }
        setCurrencyPreference(currencyCharCode)
        setConvertedBalanceChar(currencyCharCode)
        viewModel.setCurrency(currencyCharCode)
        setCurrencyChar(currencyCharCode)
        adapter.currencyChar = currencyChar
        adapter.notifyDataSetChanged()
    }

    private fun setCurrencyChar(currencyCharCode: String) {
        when (currencyCharCode) {
            GBP -> currencyChar = getString(R.string.char_gbp)
            EUR -> currencyChar = getString(R.string.char_eur)
            RUB -> currencyChar = getString(R.string.char_rub)
        }
    }

    private fun setConvertedBalanceChar(currencyCharCode: String) {
        when (currencyCharCode) {
            GBP -> binding.tvConvertedBalanceChar.text = getString(R.string.char_gbp)
            EUR -> binding.tvConvertedBalanceChar.text = getString(R.string.char_eur)
            RUB -> binding.tvConvertedBalanceChar.text = getString(R.string.char_rub)
        }
    }

    private fun deselectCurrency(cardView: CardView) {
        cardView.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        val constraintLayout = cardView.getChildAt(0) as ViewGroup
        for (i in 0 until constraintLayout.childCount) {
            val view = constraintLayout.getChildAt(i)
            if (view is TextView) {
                view.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
            }
        }
    }

    private fun setObservers() {
        viewModel.getCardUseCase(cardNumber).observe(viewLifecycleOwner) { card ->
            with(binding) {
                ivCardLogo.setImageResource(card.iconId)
                tvCardNumber.text = card.cardNumber
                tvCardholderName.text = card.cardholderName
                tvValid.text = card.valid
                val balanceString = getString(R.string.char_usd) + card.balance.toString()
                tvBalance.text = balanceString
                rvTransactionHistory.adapter = adapter

                viewModel.currencyRatio.observe(viewLifecycleOwner) { currencyRatio ->
                    val convertedBalance = card.balance?.times(currencyRatio)
                    val convertedBalanceString = String.format("%.2f", convertedBalance)
                    tvConvertedBalance.text = convertedBalanceString

                    adapter.submitList(card.transactionHistory?.map { transaction ->
                        transaction.apply {
                            convertedAmount = amount?.times(currencyRatio)
                        }
                    })
                }
            }
        }
    }

    private fun launchMyCardsFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_container, MyCardsFragment.newInstance(false))
            .commit()
    }

    private fun setCurrencyPreference(currency: String) {
        val preferences =
            requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(CURRENCY_PREFERENCE, currency)
        editor.apply()
    }

    private fun parseCardNumberPreference() {
        cardNumber =
            preferences.getString(CARD_NUMBER, UNDEFINED_CARD_NUMBER) ?: UNDEFINED_CARD_NUMBER
    }

    private fun parseCurrencyPreference() {
        when (preferences.getString(CURRENCY_PREFERENCE, GBP) ?: GBP) {
            GBP -> selectCurrency(binding.cvButtonGBP, GBP)
            EUR -> selectCurrency(binding.cvButtonEUR, EUR)
            RUB -> selectCurrency(binding.cvButtonRUB, RUB)
        }
    }


    companion object {

        private const val GBP = "GBP"
        private const val EUR = "EUR"
        private const val RUB = "RUB"
        private const val UNDEFINED_CURRENCY_CHAR = ""
        private const val CURRENCY_PREFERENCE = "currency preference"

        fun newInstance() = MainFragment()
    }
}