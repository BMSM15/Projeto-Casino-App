package com.example.projetopa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projetopa.databinding.FragmentMenuprincipalBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MenuPricipalFragment : Fragment() {

    private var _binding: FragmentMenuprincipalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuprincipalBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonJogador.setOnClickListener {
            findNavController().navigate(R.id.action_MenuPrincipalFragment_to_ListaJogadoresFragment)

            val activity = activity as MainActivity
            activity.fragment = this
            activity.idMenuAtual = R.menu.menu_main
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_settings -> true
            else -> false
        }
}