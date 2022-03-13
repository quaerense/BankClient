package org.quaerense.bankclient.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.quaerense.bankclient.databinding.FragmentMyCardsBinding

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

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}