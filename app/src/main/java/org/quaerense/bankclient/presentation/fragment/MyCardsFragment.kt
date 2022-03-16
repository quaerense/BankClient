package org.quaerense.bankclient.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.quaerense.bankclient.R
import org.quaerense.bankclient.databinding.FragmentMyCardsBinding
import org.quaerense.bankclient.presentation.MainActivity.Companion.APP_PREFERENCES
import org.quaerense.bankclient.presentation.MainActivity.Companion.CARD_NUMBER
import org.quaerense.bankclient.presentation.MainActivity.Companion.UNDEFINED_CARD_NUMBER
import org.quaerense.bankclient.presentation.adapter.CardsAdapter
import org.quaerense.bankclient.presentation.viewmodel.MyCardsViewModel

class MyCardsFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private var _binding: FragmentMyCardsBinding? = null
    private val binding: FragmentMyCardsBinding
        get() = _binding ?: throw RuntimeException("MyCardsFragment is null")

    private var cardNumber = UNDEFINED_CARD_NUMBER

    private var isFirstLaunch = true

    private val viewModel by lazy {
        ViewModelProvider(this)[MyCardsViewModel::class.java]
    }

    private val preferences by lazy {
        requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
        parseCardNumberPreference()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyCardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isFirstLaunch) {
            binding.ivButtonBack.visibility = View.GONE
        } else {
            binding.ivButtonBack.visibility = View.VISIBLE
        }
        binding.ivButtonBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        val adapter = CardsAdapter(cardNumber)
        adapter.onCardClickListener = { card ->
            card.cardNumber?.let { cardNumber ->
                launchMainFragment(cardNumber)
            }
        }
        binding.rvCards.adapter = adapter
        viewModel.getCardListUseCase().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.srlLoadCardList.setOnRefreshListener(this)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onRefresh() {
        binding.srlLoadCardList.isRefreshing = true
        loadData()
        binding.srlLoadCardList.isRefreshing = false
    }

    private fun loadData() {
        viewModel.loadData()
    }

    private fun launchMainFragment(cardNumber: String) {
        setPreferences(cardNumber)
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFragment.newInstance())
            .commit()
    }

    private fun setPreferences(cardNumber: String) {
        val preferences =
            requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(CARD_NUMBER, cardNumber)
        editor.apply()
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(PARAM_FIRST_LAUNCH)) {
            throw RuntimeException("Param first launch is absent")
        }

        isFirstLaunch = args.getBoolean(PARAM_FIRST_LAUNCH)
    }

    private fun parseCardNumberPreference() {
        cardNumber =
            preferences.getString(CARD_NUMBER, UNDEFINED_CARD_NUMBER) ?: UNDEFINED_CARD_NUMBER
    }

    companion object {

        private const val PARAM_FIRST_LAUNCH = "first launch"

        fun newInstance(isFirstLaunch: Boolean): MyCardsFragment {
            return MyCardsFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(PARAM_FIRST_LAUNCH, isFirstLaunch)
                }
            }
        }
    }
}