package com.example.hp_aksesoris.UI

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hp_aksesoris.application.AccessorisApp
import com.example.hp_aksesoris.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
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

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter= AccessorisListAdapter {accessoris ->

            val action= FirstFragmentDirections.actionFirstFragmentToSecondFragment(accessoris)
            findNavController().navigate(action)
        }
        binding.dataRecyclerView.adapter= adapter
        binding.dataRecyclerView.layoutManager= LinearLayoutManager(context)
        accessorisViewModel.allAccessoris.observe(viewLifecycleOwner) {accessories ->
            accessories.let {
                if (accessories.isEmpty()) {
                    binding.emptyTextView.visibility= View.VISIBLE
                    binding.illustrationImageView.visibility= View.VISIBLE
                }else{
                    binding.emptyTextView.visibility= View.GONE
                    binding.illustrationImageView.visibility= View.GONE
                }
                adapter.submitList(accessories)
            }
        }

        binding.addFAB.setOnClickListener {
            val action= FirstFragmentDirections.actionFirstFragmentToSecondFragment(null)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}