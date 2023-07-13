package com.example.hp_aksesoris.UI

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hp_aksesoris.application.AccessorisApp
import com.example.hp_aksesoris.databinding.FragmentSecondBinding
import com.example.hp_aksesoris.model.Accessoris

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val accessorisViewModel: AccessorisViewModel by viewModels {
        AccessorisViewModelFactory((applicationContext as AccessorisApp).repository)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext= requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = binding.nameEditText.text
        val address = binding.addressEditText.text
        binding.saveButton.setOnClickListener {
            val accessoris= Accessoris(0, name.toString(), address.toString())
            accessorisViewModel.insert(accessoris)
            findNavController().popBackStack() // Untuk dismiss halaman ini
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}