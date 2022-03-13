package org.quaerense.bankclient.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.quaerense.bankclient.R
import org.quaerense.bankclient.databinding.FragmentMyCardsBinding
import org.quaerense.bankclient.presentation.adapter.CardsAdapter
import org.quaerense.bankclient.presentation.viewmodel.MyCardsViewModel

class MyCardsFragment : Fragment() {

    private var _binding: FragmentMyCardsBinding? = null
    private val binding: FragmentMyCardsBinding
        get() = _binding ?: throw RuntimeException("MyCardsFragment is null")

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

        val adapter = CardsAdapter()
        adapter.onCardClickListener = { card ->
            card.cardNumber?.let { cardNumber ->
                startMainFragment(cardNumber)
            }
        }
        binding.rvCards.adapter = adapter

        val viewModel = ViewModelProvider(this)[MyCardsViewModel::class.java]
        viewModel.getCardListUseCase().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun startMainFragment(cardNumber: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFragment.newInstance(cardNumber))
            .commit()
    }

    companion object {

        fun newInstance() = MainFragment()
    }
}