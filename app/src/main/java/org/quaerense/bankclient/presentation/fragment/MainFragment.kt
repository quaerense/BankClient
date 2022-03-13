package org.quaerense.bankclient.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.quaerense.bankclient.R
import org.quaerense.bankclient.databinding.FragmentMainBinding
import org.quaerense.bankclient.presentation.MainActivity.Companion.APP_PREFERENCES
import org.quaerense.bankclient.presentation.MainActivity.Companion.CARD_NUMBER
import org.quaerense.bankclient.presentation.MainActivity.Companion.UNDEFINED_CARD_NUMBER
import org.quaerense.bankclient.presentation.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private var cardNumber: String = UNDEFINED_CARD_NUMBER

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parsePreferences()
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

        binding.cvCard.setOnClickListener {
            launchMyCardsFragment()
        }
        viewModel.getCardUseCase(cardNumber).observe(viewLifecycleOwner) {
            binding.tvBalance.text = it.balance.toString()
        }
    }

    private fun launchMyCardsFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MyCardsFragment.newInstance(false))
            .commit()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun parsePreferences() {
        val preferences =
            requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        cardNumber =
            preferences.getString(CARD_NUMBER, UNDEFINED_CARD_NUMBER) ?: UNDEFINED_CARD_NUMBER
    }

    companion object {

        fun newInstance() = MainFragment()
    }
}